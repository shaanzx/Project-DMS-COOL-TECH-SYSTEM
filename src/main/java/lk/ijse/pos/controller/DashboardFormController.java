package lk.ijse.pos.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DashboardFormController implements Initializable {

    @FXML
    private BarChart<?, ?> barChart;

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

    }

    private void setPieChart() {

    }

    private void getRepairCount() {

    }

    private void countOrder() {

    }

}
