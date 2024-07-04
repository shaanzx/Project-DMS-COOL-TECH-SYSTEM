package lk.ijse.pos.dao.custom.impl;

import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import lk.ijse.pos.bo.custom.ItemBO;
import lk.ijse.pos.dao.SQLUtil;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.entity.Item;
import lk.ijse.pos.entity.OrderDetails;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl  implements ItemDAO {
    @Override
    public ObservableList<XYChart.Series<String, Integer>> getDataToBarChart() throws SQLException {
        return null;
    }

    @Override
    public List<String> getItemCodes() throws SQLException, ClassNotFoundException {
        List<String> itemCodes = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute("SELECT iCode FROM item");
         while (resultSet.next()) {
            itemCodes.add(resultSet.getString(1 ));
         }
         return itemCodes;
    }

    @Override
    public boolean updateItemQty(List<OrderDetails> orderDetails) throws SQLException {
        return false;
    }

    @Override
    public ResultSet generateId() throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT iCode FROM item ORDER BY iCode DESC LIMIT 1");
    }

    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException  {
        ArrayList<Item> itemList = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM item");
        while (resultSet.next()) {
            itemList.add(new Item(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getDouble(5),
                    resultSet.getString(6)
            ));
        }
        return itemList;
    }

    @Override
    public boolean save(Item entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO item VALUES(?,?,?,?,?,?)",
                entity.getCode(),
                entity.getDescription(),
                entity.getModel(),
                entity.getQtyOnHand(),
                entity.getUnitPrice(),
                entity.getDate());
    }

    @Override
    public boolean update(Item entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE item SET iName = ?, iCategory = ?, qtyOnHand = ?, iPrice = ?, date = ? WHERE iCode = ?",
                entity.getDescription(),
                entity.getModel(),
                entity.getQtyOnHand(),
                entity.getUnitPrice(),
                entity.getDate(),
                entity.getCode());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM item WHERE iCode = ?",id);
    }

    @Override
    public Item search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM item WHERE iCode = ?",id);
        Item item = null;


        if (resultSet.next()) {
            String iCode = resultSet.getString(1);
            String description = resultSet.getString(2);
            String model = resultSet.getString(3);
            int qtyOnHand = resultSet.getInt(4);
            double unitPrice = resultSet.getDouble(5);
            String date = resultSet.getString(6);

            item = new Item(iCode, description, model, qtyOnHand, unitPrice, date);
        }
        return item;
    }
}
