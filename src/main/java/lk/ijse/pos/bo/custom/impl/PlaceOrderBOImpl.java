package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.PlaceOrderBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.dao.custom.OrderDAO;
import lk.ijse.pos.dao.custom.OrderDetailsDAO;

public class PlaceOrderBOImpl implements PlaceOrderBO {
    OrderDAO orderDao = (OrderDAO) DAOFactory.getDaoFactory().getDaoType(DAOFactory.DAOType.ORDER);
    OrderDetailsDAO orderDetailsDao = (OrderDetailsDAO) DAOFactory.getDaoFactory().getDaoType(DAOFactory.DAOType.ORDER_DETAILS);
    ItemDAO itemDao = (ItemDAO) DAOFactory.getDaoFactory().getDaoType(DAOFactory.DAOType.ITEM);
}
