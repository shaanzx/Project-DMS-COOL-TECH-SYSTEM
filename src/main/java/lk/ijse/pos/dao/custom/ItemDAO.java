package lk.ijse.pos.dao.custom;

import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import lk.ijse.pos.dao.CrudDAO;
import lk.ijse.pos.entity.Item;
import lk.ijse.pos.entity.OrderDetails;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ItemDAO extends CrudDAO<Item> {
    ObservableList<XYChart.Series<String, Integer>> getDataToBarChart() throws SQLException, ClassNotFoundException;

    ArrayList<PieChart.Data> getPieChartData() throws SQLException, ClassNotFoundException;

    List<String> getItemCodes() throws SQLException, ClassNotFoundException;

    boolean updateItemQty(List<OrderDetails> orderDetails) throws SQLException, ClassNotFoundException;

}
