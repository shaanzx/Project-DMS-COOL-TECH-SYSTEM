package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.ViewOrderBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.QueryDAO;
import lk.ijse.pos.dto.ViewOrdersDTO;
import lk.ijse.pos.entity.ViewOrders;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewOrderBOImpl implements ViewOrderBO {
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDaoType(DAOFactory.DAOType.VIEW_ORDER);

    @Override
    public List<ViewOrdersDTO> getViewOrders() throws SQLException, ClassNotFoundException {
        List<ViewOrdersDTO> viewOrdersDTOs = new ArrayList<>();
        List<ViewOrders> viewOrders = queryDAO.getViewOrders();
        for(ViewOrders viewOrder : viewOrders){
            viewOrdersDTOs.add(new ViewOrdersDTO(
                    viewOrder.getCustomerId(),
                    viewOrder.getOrderId(),
                    viewOrder.getDate(),
                    viewOrder.getItemCode(),
                    viewOrder.getItemQty(),
                    viewOrder.getItemUnitPrice(),
                    viewOrder.getTotalAmount()
            ));
        }
        return viewOrdersDTOs;
    }

    @Override
    public List<ViewOrdersDTO> getViewOrdersByOrderId(String orderId) throws SQLException, ClassNotFoundException {
        List<ViewOrdersDTO> viewOrdersDTOs = new ArrayList<>();
        List<ViewOrders> viewOrdersByOrderId = queryDAO.getViewOrdersByOrderId(orderId);
        for(ViewOrders viewOrder : viewOrdersByOrderId){
            viewOrdersDTOs.add(new ViewOrdersDTO(
                    viewOrder.getCustomerId(),
                    viewOrder.getOrderId(),
                    viewOrder.getDate(),
                    viewOrder.getItemCode(),
                    viewOrder.getItemQty(),
                    viewOrder.getItemUnitPrice(),
                    viewOrder.getTotalAmount()
            ));
        }
        return viewOrdersDTOs;
    }
}
