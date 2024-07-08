package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class RepairFormController {

    @FXML
    private JFXButton btnOrderPlace;

    @FXML
    private JFXComboBox<?> cmbEmployeeId;

    @FXML
    private JFXComboBox<?> cmbItemCode;

    @FXML
    private JFXComboBox<?> cmbVehicleNo;

    @FXML
    private TableColumn<?, ?> colIetmQty;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colItemUnitPrice;

    @FXML
    private TableColumn<?, ?> colRemove;

    @FXML
    private TableColumn<?, ?> colRepairCost;

    @FXML
    private TableColumn<?, ?> colRepairDate;

    @FXML
    private TableColumn<?, ?> colRepairDescription;

    @FXML
    private TableColumn<?, ?> colTotalPrice;

    @FXML
    private TableColumn<?, ?> colVehicleNo;

    @FXML
    private DatePicker dpRepairDate;

    @FXML
    private Label lblBalance;

    @FXML
    private Label lblCustomerName;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblEmployeeName;

    @FXML
    private Label lblItemName;

    @FXML
    private Label lblItemQtyOnHand;

    @FXML
    private Label lblMoreMoney;

    @FXML
    private Label lblNeeded;

    @FXML
    private Label lblNetAmount;

    @FXML
    private Label lblOrderId;

    @FXML
    private Label lblRepairId;

    @FXML
    private Label lblUnitPrice;

    @FXML
    private Pane pagingPane;

    @FXML
    private TableView<?> tblRepairDetails;

    @FXML
    private TextField txtPayment;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtRepairCost;

    @FXML
    private TextField txtRepairDescription;

    @FXML
    void btnAddNewVehicleOnAction(ActionEvent event) {

    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {

    }

    @FXML
    void btnConfirmRepairBillOnAction(ActionEvent event) {

    }

    @FXML
    void btnViewRepairDetailsOnAction(ActionEvent event) {

    }

    @FXML
    void cmbEmployeeIdOnAction(ActionEvent event) {

    }

    @FXML
    void cmbItemCodeOnAction(ActionEvent event) {

    }

    @FXML
    void cmbVehicleNoOnAction(ActionEvent event) {

    }

    @FXML
    void keyCash(KeyEvent event) {

    }

    @FXML
    void txtQtyOnAction(ActionEvent event) {

    }

}
