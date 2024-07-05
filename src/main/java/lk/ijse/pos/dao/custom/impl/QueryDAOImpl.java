package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.SQLUtil;
import lk.ijse.pos.dao.custom.QueryDAO;
import lk.ijse.pos.entity.ViewOrders;
import lk.ijse.pos.entity.ViewRepair;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {

    @Override
    public List<ViewOrders> getViewOrders() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT cId,orders.oId,ODate,iCode,qty,unitPrice,oPrice FROM orderdetails JOIN orders ON orderdetails.oid = orders.oid");
        ArrayList<ViewOrders> viewOrderList = new ArrayList<>();
        while (resultSet.next()) {
            viewOrderList.add(new ViewOrders(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDate(3),
                    resultSet.getString(4),
                    resultSet.getInt(5),
                    resultSet.getDouble(6),
                    resultSet.getDouble(7)));
        }
        return viewOrderList;
    }

    @Override
    public List<ViewOrders> getViewOrdersByOrderId(String orderId) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT cId,orders.oId,ODate,iCode,qty,unitPrice,oPrice FROM orderdetails JOIN orders ON orderdetails.oid = orders.oid WHERE orders.oid = ?",orderId);

        List<ViewOrders> viewOrdersList = new ArrayList<>();

        while (resultSet.next()) {
            viewOrdersList.add(new ViewOrders(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDate(3),
                    resultSet.getString(4),
                    resultSet.getInt(5),
                    resultSet.getDouble(6),
                    resultSet.getDouble(7)));
        }
        return viewOrdersList;
    }

    @Override
    public List<ViewRepair> getViewRepair() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT rId,vNo,repairDate,orderdetails.iCode,orderdetails.qty,item.iPrice,repairCost,totalPrice FROM repair JOIN orderdetails ON repair.iCode = orderdetails.iCode JOIN Item ON repair.iCode = Item.iCode");
        ArrayList<ViewRepair> viewRepairList = new ArrayList<>();
        while (resultSet.next()) {
            viewRepairList.add(new ViewRepair(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDate(3),
                    resultSet.getString(4),
                    resultSet.getInt(5),
                    resultSet.getDouble(6),
                    resultSet.getDouble(7),
                    resultSet.getDouble(8)
            ));
        }
        return viewRepairList;
    }

    @Override
    public List<ViewRepair> getViewRepairByRepairId(String repairId) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT rId,vNo,repairDate,orderdetails.iCode,orderdetails.qty,item.iPrice,repairCost,totalPrice FROM repair JOIN orderdetails ON repair.iCode = orderdetails.iCode JOIN Item ON repair.iCode = Item.iCode WHERE repair.rId = ?",repairId);
        ArrayList<ViewRepair> viewRepairList = new ArrayList<>();
        while (resultSet.next()) {
            viewRepairList.add(new ViewRepair(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDate(3),
                    resultSet.getString(4),
                    resultSet.getInt(5),
                    resultSet.getDouble(6),
                    resultSet.getDouble(7),
                    resultSet.getDouble(8)
            ));
        }
        return viewRepairList;
    }
}
