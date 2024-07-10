package lk.ijse.pos.dao.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dao.CrudDAO;
import lk.ijse.pos.dao.SuperDAO;
import lk.ijse.pos.entity.Order;

import java.sql.SQLException;

public interface OrderDAO extends CrudDAO<Order> {

    int countOrderId() throws SQLException, ClassNotFoundException;
}
