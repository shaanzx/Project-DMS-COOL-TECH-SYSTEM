package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.PlaceOrderBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.dao.custom.OrderDAO;
import lk.ijse.pos.dao.custom.OrderDetailsDAO;
import lk.ijse.pos.dao.custom.PaymentDAO;
import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.dto.OrderDTO;
import lk.ijse.pos.dto.OrderDetailsDTO;
import lk.ijse.pos.dto.OrderPlaceDTO;
import lk.ijse.pos.entity.Order;
import lk.ijse.pos.entity.OrderDetails;
import lk.ijse.pos.entity.Payment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlaceOrderBOImpl implements PlaceOrderBO {
    OrderDAO orderDao = (OrderDAO) DAOFactory.getDaoFactory().getDaoType(DAOFactory.DAOType.ORDER);
    OrderDetailsDAO orderDetailsDao = (OrderDetailsDAO) DAOFactory.getDaoFactory().getDaoType(DAOFactory.DAOType.ORDER_DETAILS);
    ItemDAO itemDao = (ItemDAO) DAOFactory.getDaoFactory().getDaoType(DAOFactory.DAOType.ITEM);
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDaoType(DAOFactory.DAOType.PAYMENT);

    @Override
    public ResultSet generateNextOrderId() throws SQLException, ClassNotFoundException {
        return orderDao.generateId();
    }

    @Override
    public ArrayList<OrderDTO> getAllOrder() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean saveOrder(OrderDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateOrder(OrderDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteOrder(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public OrderDTO searchOrder(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public  boolean orderPlace(OrderPlaceDTO orderPlacedto) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        connection.setAutoCommit(false);

        try{
            boolean isOdSaved = orderDao.save(new Order(
                    orderPlacedto.getOrderDTO().getOrderId(),
                    orderPlacedto.getOrderDTO().getCustomerId(),
                    orderPlacedto.getOrderDTO().getDate()
            ));
            if(isOdSaved) {
                System.out.println(isOdSaved);
                ArrayList<OrderDetails> orderDetailsArrayList = new ArrayList<>();
                for (OrderDetailsDTO od : orderPlacedto.getOrderDetailsDTO()) {
                    OrderDetails orderDetails = new OrderDetails(
                            od.getOrderId(),
                            od.getItemCode(),
                            od.getOrderDate(),
                            od.getQty(),
                            od.getUnitPrice(),
                            od.getOrderAmount());
                    orderDetailsArrayList.add(orderDetails);
                }
                boolean isOdDetailsSaved = orderDetailsDao.save(orderDetailsArrayList);
                if (isOdDetailsSaved) {
                    System.out.println(isOdDetailsSaved);
                    ArrayList<OrderDetails> itemDetailsArrayList = new ArrayList<>();
                    for (OrderDetailsDTO od : orderPlacedto.getOrderDetailsDTO()) {
                        OrderDetails orderDetails = new OrderDetails(
                                od.getOrderId(),
                                od.getItemCode(),
                                od.getOrderDate(),
                                od.getQty(),
                                od.getUnitPrice(),
                                od.getOrderAmount());
                        itemDetailsArrayList.add(orderDetails);
                        boolean isItemUpdated = itemDao.updateItemQty(itemDetailsArrayList);
                        System.out.println(isItemUpdated);
                        if (isItemUpdated) {
                            System.out.println(orderPlacedto.getPaymentDTO());
                            boolean isPaymentSaved = paymentDAO.save(new Payment(
                                    orderPlacedto.getPaymentDTO().getPaymentId(),
                                    orderPlacedto.getPaymentDTO().getCustomerId(),
                                    orderPlacedto.getPaymentDTO().getOrderId(),
                                    orderPlacedto.getPaymentDTO().getRepairId(),
                                    orderPlacedto.getPaymentDTO().getTotalAmount(),
                                    orderPlacedto.getPaymentDTO().getPaymentDate(),
                                    orderPlacedto.getPaymentDTO().getPayCash(),
                                    orderPlacedto.getPaymentDTO().getBalance()
                            ));
                            if (isPaymentSaved) {
                                connection.commit();
                                return true;
                            }
                        }
                    }
                }
            }
            connection.rollback();
            return false;
        }catch (SQLException e){
            connection.rollback();
            return false;
        }finally {
            connection.setAutoCommit(true);
        }
    }
}
