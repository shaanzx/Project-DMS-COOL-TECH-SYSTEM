package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.PlaceOrderBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.dao.custom.OrderDAO;
import lk.ijse.pos.dao.custom.OrderDetailsDAO;
import lk.ijse.pos.dto.OrderDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlaceOrderBOImpl implements PlaceOrderBO {
    OrderDAO orderDao = (OrderDAO) DAOFactory.getDaoFactory().getDaoType(DAOFactory.DAOType.ORDER);
    OrderDetailsDAO orderDetailsDao = (OrderDetailsDAO) DAOFactory.getDaoFactory().getDaoType(DAOFactory.DAOType.ORDER_DETAILS);
    ItemDAO itemDao = (ItemDAO) DAOFactory.getDaoFactory().getDaoType(DAOFactory.DAOType.ITEM);

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
}
