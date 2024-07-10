package lk.ijse.pos.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.ItemBO;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DashboardFormController implements Initializable {
    ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBOType(BOFactory.BOType.ITEM);

    @FXML
    private BarChart<String, Integer> barChart;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblOrderCount;

    @FXML
    private Label lblRepairCount;

    @FXML
    private Pane pagingPane;

    @FXML
    private PieChart pieChart;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        countOrder();
        getRepairCount();
        setPieChart();
        setDate();
        //timenow();
        setBarChart();
    }

    private void setDate() {
        lblDate.setText(String.valueOf(LocalDate.now()));
    }

    private void setBarChart() {

        try {
            ObservableList<XYChart.Series<String, Integer>> barChartData  = itemBO.getDataToBarChart();
            barChart.setData(barChartData);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        ;
    }

    private void setPieChart() {

    }

    private void getRepairCount() {

    }

    private void countOrder() {

    }

}
