package lk.ijse.pos.controller;

import javafx.collections.FXCollections;
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
import lk.ijse.pos.bo.custom.OrderBO;
import lk.ijse.pos.bo.custom.RepairBO;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DashboardFormController implements Initializable {
    ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBOType(BOFactory.BOType.ITEM);
    OrderBO orderBO = (OrderBO) BOFactory.getBoFactory().getBOType(BOFactory.BOType.ORDER);
    RepairBO repairBO = (RepairBO) BOFactory.getBoFactory().getBOType(BOFactory.BOType.REPAIR);

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
        try {
            ObservableList<PieChart.Data> obList = FXCollections.observableArrayList();
            ArrayList<PieChart.Data> data = itemBO.getPieChartData();
            for (PieChart.Data datum : data) {
                obList.add(datum);
            }
            pieChart.getData().addAll(obList);
            pieChart.setTitle("Most Trending Products");
            pieChart.setStartAngle(180);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void getRepairCount() {
        try {
            int i = repairBO.countRepairId();
            lblRepairCount.setText(String.valueOf(i));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void countOrder() {
        try {
            int count = orderBO.countOrderId();
            lblOrderCount.setText(String.valueOf( count));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
