package lk.ijse.pos.controller;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class DashboardFormController {

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

}
