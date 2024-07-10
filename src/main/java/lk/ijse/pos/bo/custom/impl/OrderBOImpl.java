package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.OrderBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.OrderDAO;
import lk.ijse.pos.dao.custom.QueryDAO;
import lk.ijse.pos.dto.ViewOrdersDTO;
import lk.ijse.pos.dto.ViewRepairDTO;
import lk.ijse.pos.entity.ViewOrders;
import lk.ijse.pos.entity.ViewRepair;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderBOImpl implements OrderBO {
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDaoType(DAOFactory.DAOType.VIEW_ORDER);
    QueryDAO getQueryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDaoType(DAOFactory.DAOType.VIEW_REPAIR);
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDaoType(DAOFactory.DAOType.ORDER);
    @Override
    public int countOrderId() throws SQLException, ClassNotFoundException {
        return orderDAO.countOrderId();
    }

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

    @Override
    public List<ViewRepairDTO> getViewRepair() throws SQLException, ClassNotFoundException {
        ArrayList<ViewRepairDTO> viewRepairDTOs = new ArrayList<>();
        List<ViewRepair> viewRepairs = getQueryDAO.getViewRepair();
        for(ViewRepair viewRepair : viewRepairs){
            viewRepairDTOs.add(new ViewRepairDTO(
                    viewRepair.getRepairId(),
                    viewRepair.getVehicleNo(),
                    viewRepair.getRepairDate(),
                    viewRepair.getItemCode(),
                    viewRepair.getItemQty(),
                    viewRepair.getItemUnitPrice(),
                    viewRepair.getRepairCost(),
                    viewRepair.getTotalPrice()
            ));
        }
        return viewRepairDTOs;
    }

    @Override
    public List<ViewRepairDTO> getViewRepairByRepairId(String repairId) throws SQLException, ClassNotFoundException {
        ArrayList<ViewRepairDTO> viewRepairDTOArrayList = new ArrayList<>();
        List<ViewRepair> viewRepairByRepairId = getQueryDAO.getViewRepairByRepairId(repairId);
        for (ViewRepair viewRepair : viewRepairByRepairId){
            viewRepairDTOArrayList.add(new ViewRepairDTO(
                    viewRepair.getRepairId(),
                    viewRepair.getVehicleNo(),
                    viewRepair.getRepairDate(),
                    viewRepair.getItemCode(),
                    viewRepair.getItemQty(),
                    viewRepair.getItemUnitPrice(),
                    viewRepair.getRepairCost(),
                    viewRepair.getTotalPrice()
            ));
        }
        return viewRepairDTOArrayList;
    }
}
