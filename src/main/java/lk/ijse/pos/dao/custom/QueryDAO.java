package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.SuperDAO;
import lk.ijse.pos.entity.ViewOrders;
import lk.ijse.pos.entity.ViewRepair;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface QueryDAO extends SuperDAO {
    List<ViewOrders> getViewOrders() throws SQLException, ClassNotFoundException;

    List<ViewOrders> getViewOrdersByOrderId(String orderId) throws SQLException, ClassNotFoundException;

    List<ViewRepair> getViewRepair() throws SQLException, ClassNotFoundException;

    List<ViewRepair> getViewRepairByRepairId(String repairId) throws SQLException, ClassNotFoundException;

}
