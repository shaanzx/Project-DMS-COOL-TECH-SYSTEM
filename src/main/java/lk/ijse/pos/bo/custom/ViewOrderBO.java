package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.ViewOrdersDTO;
import lk.ijse.pos.entity.ViewOrders;

import java.sql.SQLException;
import java.util.List;

public interface ViewOrderBO extends SuperBO {
    List<ViewOrdersDTO> getViewOrders() throws SQLException, ClassNotFoundException;

    List<ViewOrdersDTO> getViewOrdersByOrderId(String orderId) throws SQLException, ClassNotFoundException;
}
