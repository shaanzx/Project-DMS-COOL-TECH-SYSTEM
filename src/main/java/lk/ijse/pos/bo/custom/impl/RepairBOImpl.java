package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.RepairBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.*;
import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.dto.OrderDetailsDTO;
import lk.ijse.pos.dto.RepairDTO;
import lk.ijse.pos.dto.RepairDetailsDTO;
import lk.ijse.pos.entity.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RepairBOImpl implements RepairBO {
    RepairDAO repairDAO = (RepairDAO) DAOFactory.getDaoFactory().getDaoType(DAOFactory.DAOType.REPAIR);
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDaoType(DAOFactory.DAOType.ORDER);
    OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getDaoFactory().getDaoType(DAOFactory.DAOType.ORDER_DETAILS);
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDaoType(DAOFactory.DAOType.ITEM);
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDaoType(DAOFactory.DAOType.PAYMENT);

    @Override
    public int countRepairId() throws SQLException {
        return 0;
    }

    @Override
    public ResultSet generateRepairId() throws SQLException, ClassNotFoundException {
        return repairDAO.generateId();
    }

    @Override
    public ArrayList<RepairDTO> getAllRepair() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean saveRepair(RepairDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateRepair(RepairDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteRepair(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public RepairDTO searchRepair(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean addNewRepair(RepairDetailsDTO repairDetails) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        connection.setAutoCommit(false);

        try {
            boolean isOrderSaved = orderDAO.save(new Order(
                    repairDetails.getOrder().getOrderId(),
                    repairDetails.getOrder().getCustomerId(),
                    repairDetails.getOrder().getDate()));
            if (isOrderSaved) {
                ArrayList<OrderDetails> orderDetailsArrayList = new ArrayList<>();
                for (OrderDetailsDTO od : repairDetails.getOrderDetails()) {
                    OrderDetails orderDetails = new OrderDetails(
                            od.getOrderId(),
                            od.getItemCode(),
                            od.getOrderDate(),
                            od.getQty(),
                            od.getUnitPrice(),
                            od.getOrderAmount());
                    orderDetailsArrayList.add(orderDetails);
                }
                boolean isOrderDetailsSaved = orderDetailsDAO.save(orderDetailsArrayList);
                if (isOrderDetailsSaved) {
                    ArrayList<OrderDetails> itemDetailsArrayList = new ArrayList<>();
                    for (OrderDetailsDTO od : repairDetails.getOrderDetails()) {
                        OrderDetails orderDetails = new OrderDetails(
                                od.getOrderId(),
                                od.getItemCode(),
                                od.getOrderDate(),
                                od.getQty(),
                                od.getUnitPrice(),
                                od.getOrderAmount());
                        itemDetailsArrayList.add(orderDetails);
                        boolean isItemUpdated = itemDAO.updateItemQty(itemDetailsArrayList);
                    if (isItemUpdated) {
                        boolean isRepairSaved = repairDAO.save(new Repair(
                                repairDetails.getRepair().getRepairId(),
                                repairDetails.getRepair().getVehicleNo(),
                                repairDetails.getRepair().getDescription(),
                                repairDetails.getRepair().getRepairDate(),
                                repairDetails.getRepair().getRepairCost(),
                                repairDetails.getRepair().getEmployeeId(),
                                repairDetails.getRepair().getItemCode(),
                                repairDetails.getRepair().getTotalAmount()
                        ));
                        if (isRepairSaved) {
                            boolean isPaymentSaved = paymentDAO.save(new Payment(
                                    repairDetails.getPayment().getPaymentId(),
                                    repairDetails.getPayment().getCustomerId(),
                                    repairDetails.getPayment().getOrderId(),
                                    repairDetails.getPayment().getRepairId(),
                                    repairDetails.getPayment().getTotalAmount(),
                                    repairDetails.getPayment().getPaymentDate(),
                                    repairDetails.getPayment().getPayCash(),
                                    repairDetails.getPayment().getBalance()
                            ));
                            if (isPaymentSaved) {
                                connection.commit();
                                return true;
                            }
                        }
                        }
                    }
                }
            }
            connection.rollback();
            return false;
        } catch (SQLException e) {
            connection.rollback();
            return false;

        } finally {
            connection.setAutoCommit(true);
        }
    }
}
