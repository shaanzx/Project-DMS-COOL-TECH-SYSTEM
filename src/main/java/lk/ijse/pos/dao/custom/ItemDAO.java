package lk.ijse.pos.dao.custom;

import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import lk.ijse.pos.dao.CrudDAO;
import lk.ijse.pos.entity.Item;

import java.sql.SQLException;

public interface ItemDAO extends CrudDAO<Item> {
    ObservableList<XYChart.Series<String, Integer>> getDataToBarChart() throws SQLException;
}
