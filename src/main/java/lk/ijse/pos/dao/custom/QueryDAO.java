package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.SuperDAO;
import lk.ijse.pos.entity.ViewOrders;

import java.sql.SQLException;
import java.util.List;

public interface QueryDAO extends SuperDAO {
    List<ViewOrders> getViewOrders() throws SQLException, ClassNotFoundException;

    List<ViewOrders> getViewOrdersByOrderId(String orderId) throws SQLException, ClassNotFoundException;
}
