package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.ViewOrdersDTO;
import lk.ijse.pos.dto.ViewRepairDTO;
import lk.ijse.pos.entity.ViewRepair;

import java.sql.SQLException;
import java.util.List;

public interface OrderBO extends SuperBO {
    int countOrderId() throws SQLException, ClassNotFoundException;

    List<ViewOrdersDTO> getViewOrders() throws SQLException, ClassNotFoundException;

    List<ViewOrdersDTO> getViewOrdersByOrderId(String orderId) throws SQLException, ClassNotFoundException;

    List<ViewRepairDTO> getViewRepair() throws SQLException, ClassNotFoundException;

    List<ViewRepairDTO> getViewRepairByRepairId(String repairId) throws SQLException, ClassNotFoundException;
}
