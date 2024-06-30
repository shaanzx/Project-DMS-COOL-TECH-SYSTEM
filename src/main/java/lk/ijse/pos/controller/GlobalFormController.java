package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import lk.ijse.pos.util.Navigation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GlobalFormController implements Initializable {

    public AnchorPane mainpane;
    @FXML
    private Pane pagingPane;

    @FXML
    private JFXButton btnCustomer;

    @FXML
    private JFXButton btnDashboard;

    @FXML
    private JFXButton btnEmployee;

    @FXML
    private JFXButton btnOrder;

    @FXML
    private JFXButton btnRepair;

    @FXML
    private JFXButton btnStoke;

    @FXML
    private JFXButton btnVehicle;

    @FXML
    void btnCustomerOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(pagingPane, "customer_form.fxml");
        btnCustomer.setStyle("-fx-background-color: white; -fx-background-radius: 75; -fx-font-size: 20;");
        btnDashboard.setStyle("-fx-background-color:  #7ed6df; -fx-background-radius: 75; -fx-font-size: 20;");
        btnEmployee.setStyle("-fx-background-color:  #7ed6df; -fx-background-radius: 75; -fx-font-size: 20;");
        btnOrder.setStyle("-fx-background-color:  #7ed6df; -fx-background-radius: 75; -fx-font-size: 20;");
        btnRepair.setStyle("-fx-background-color:  #7ed6df; -fx-background-radius: 75; -fx-font-size: 20;");
        btnStoke.setStyle("-fx-background-color:  #7ed6df; -fx-background-radius: 75; -fx-font-size: 20;");
        btnVehicle.setStyle("-fx-background-color:  #7ed6df; -fx-background-radius: 75; -fx-font-size: 20;");

    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(pagingPane, "dashboard_form.fxml");
        btnCustomer.setStyle("-fx-background-color: #7ed6df; -fx-background-radius: 75; -fx-font-size: 20;");
        btnDashboard.setStyle("-fx-background-color:  white; -fx-background-radius: 75; -fx-font-size: 20;");
        btnEmployee.setStyle("-fx-background-color:  #7ed6df; -fx-background-radius: 75; -fx-font-size: 20;");
        btnOrder.setStyle("-fx-background-color:  #7ed6df; -fx-background-radius: 75; -fx-font-size: 20;");
        btnRepair.setStyle("-fx-background-color:  #7ed6df; -fx-background-radius: 75; -fx-font-size: 20;");
        btnStoke.setStyle("-fx-background-color:  #7ed6df; -fx-background-radius: 75; -fx-font-size: 20;");
        btnVehicle.setStyle("-fx-background-color:  #7ed6df; -fx-background-radius: 75; -fx-font-size: 20;");
    }

    @FXML
    void btnEmployeeOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(pagingPane, "employee_form.fxml");
        btnCustomer.setStyle("-fx-background-color: #7ed6df; -fx-background-radius: 75; -fx-font-size: 20;");
        btnDashboard.setStyle("-fx-background-color:  #7ed6df; -fx-background-radius: 75; -fx-font-size: 20;");
        btnEmployee.setStyle("-fx-background-color:  white; -fx-background-radius: 75; -fx-font-size: 20;");
        btnOrder.setStyle("-fx-background-color:  #7ed6df; -fx-background-radius: 75; -fx-font-size: 20;");
        btnRepair.setStyle("-fx-background-color:  #7ed6df; -fx-background-radius: 75; -fx-font-size: 20;");
        btnStoke.setStyle("-fx-background-color:  #7ed6df; -fx-background-radius: 75; -fx-font-size: 20;");
        btnVehicle.setStyle("-fx-background-color:  #7ed6df; -fx-background-radius: 75; -fx-font-size: 20;");
    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation( "new_login_form.fxml",mainpane);
    }

    @FXML
    void btnOrderOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(pagingPane, "placeOrder_form.fxml");
        btnCustomer.setStyle("-fx-background-color: #7ed6df; -fx-background-radius: 75; -fx-font-size: 20;");
        btnDashboard.setStyle("-fx-background-color:  #7ed6df; -fx-background-radius: 75; -fx-font-size: 20;");
        btnEmployee.setStyle("-fx-background-color:  #7ed6df; -fx-background-radius: 75; -fx-font-size: 20;");
        btnOrder.setStyle("-fx-background-color:  white; -fx-background-radius: 75; -fx-font-size: 20;");
        btnRepair.setStyle("-fx-background-color:  #7ed6df; -fx-background-radius: 75; -fx-font-size: 20;");
        btnStoke.setStyle("-fx-background-color:  #7ed6df; -fx-background-radius: 75; -fx-font-size: 20;");
        btnVehicle.setStyle("-fx-background-color:  #7ed6df; -fx-background-radius: 75; -fx-font-size: 20;");
    }

    @FXML
    void btnStokeOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(pagingPane, "item_from.fxml");
        btnCustomer.setStyle("-fx-background-color: #7ed6df; -fx-background-radius: 75; -fx-font-size: 20;");
        btnDashboard.setStyle("-fx-background-color:  #7ed6df; -fx-background-radius: 75; -fx-font-size: 20;");
        btnEmployee.setStyle("-fx-background-color: #7ed6df; -fx-background-radius: 75; -fx-font-size: 20;");
        btnOrder.setStyle("-fx-background-color:  #7ed6df; -fx-background-radius: 75; -fx-font-size: 20;");
        btnRepair.setStyle("-fx-background-color:  #7ed6df; -fx-background-radius: 75; -fx-font-size: 20;");
        btnStoke.setStyle("-fx-background-color:  white; -fx-background-radius: 75; -fx-font-size: 20;");
        btnVehicle.setStyle("-fx-background-color:  #7ed6df; -fx-background-radius: 75; -fx-font-size: 20;");
    }

    @FXML
    void btnVehicleOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(pagingPane, "vehicle_form.fxml");
        btnCustomer.setStyle("-fx-background-color: #7ed6df; -fx-background-radius: 75; -fx-font-size: 20;");
        btnDashboard.setStyle("-fx-background-color:  #7ed6df; -fx-background-radius: 75; -fx-font-size: 20;");
        btnEmployee.setStyle("-fx-background-color:  #7ed6df; -fx-background-radius: 75; -fx-font-size: 20;");
        btnOrder.setStyle("-fx-background-color:  #7ed6df; -fx-background-radius: 75; -fx-font-size: 20;");
        btnRepair.setStyle("-fx-background-color:  #7ed6df; -fx-background-radius: 75; -fx-font-size: 20;");
        btnStoke.setStyle("-fx-background-color:  #7ed6df; -fx-background-radius: 75; -fx-font-size: 20;");
        btnVehicle.setStyle("-fx-background-color: white; -fx-background-radius: 75; -fx-font-size: 20;");
    }

    @FXML
    void btnRepairOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(pagingPane, "repair_form.fxml");
        btnCustomer.setStyle("-fx-background-color: #7ed6df; -fx-background-radius: 75; -fx-font-size: 20;");
        btnDashboard.setStyle("-fx-background-color:  #7ed6df; -fx-background-radius: 75; -fx-font-size: 20;");
        btnEmployee.setStyle("-fx-background-color:  #7ed6df; -fx-background-radius: 75; -fx-font-size: 20;");
        btnOrder.setStyle("-fx-background-color:  #7ed6df; -fx-background-radius: 75; -fx-font-size: 20;");
        btnRepair.setStyle("-fx-background-color:  white; -fx-background-radius: 75; -fx-font-size: 20;");
        btnStoke.setStyle("-fx-background-color:  #7ed6df; -fx-background-radius: 75; -fx-font-size: 20;");
        btnVehicle.setStyle("-fx-background-color:  #7ed6df; -fx-background-radius: 75; -fx-font-size: 20;");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pagingPane.setVisible(true);
        try {
            Navigation.switchPaging(pagingPane, "customer_form.fxml");
            btnDashboard.setStyle("-fx-background-color:  white; -fx-background-radius: 75; -fx-font-size: 20;");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void imgUserOnAction(MouseEvent mouseEvent) {

    }
}
