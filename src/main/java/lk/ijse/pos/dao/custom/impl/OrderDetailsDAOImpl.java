package lk.ijse.pos.dao.custom.impl;

import javafx.scene.chart.PieChart;
import lk.ijse.pos.dao.SQLUtil;
import lk.ijse.pos.dao.custom.OrderDetailsDAO;
import lk.ijse.pos.entity.Item;
import lk.ijse.pos.entity.OrderDetails;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {
    @Override
    public ResultSet generateId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<OrderDetails> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(OrderDetails entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(OrderDetails entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public OrderDetails search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }


    @Override
    public boolean save(ArrayList<OrderDetails> orderDetailsList) throws SQLException, ClassNotFoundException {
        for(OrderDetails orderDetails : orderDetailsList){
            if(!orderSave(orderDetails)){
                return false;
            }
        }
        return true;
    }

    private boolean orderSave(OrderDetails orderDetails) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO orderdetails VALUES(?,?,?,?,?,?)",
                orderDetails.getOrderId(),
                orderDetails.getItemCode(),
                orderDetails.getOrderDate(),
                orderDetails.getQty(),
                orderDetails.getUnitPrice(),
                orderDetails.getOrderAmount());
    }
}
