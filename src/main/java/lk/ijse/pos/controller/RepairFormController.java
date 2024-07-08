package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.*;
import lk.ijse.pos.dto.EmployeeDTO;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.dto.VehicleDTO;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RepairFormController implements Initializable {
    RepairBO repairBO = (RepairBO) BOFactory.getBoFactory().getBOType(BOFactory.BOType.REPAIR);
    PlaceOrderBO placeOrderBO = (PlaceOrderBO) BOFactory.getBoFactory().getBOType(BOFactory.BOType.PLACE_ORDER);
    VehicleBO vehicleBO = (VehicleBO) BOFactory.getBoFactory().getBOType(BOFactory.BOType.VEHICLE);
    ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBOType(BOFactory.BOType.ITEM);
    EmployeeBO employeeBO = (EmployeeBO) BOFactory.getBoFactory().getBOType(BOFactory.BOType.EMPLOYEE);
    @FXML
    private JFXButton btnOrderPlace;

    @FXML
    private JFXComboBox<String> cmbEmployeeId;

    @FXML
    private JFXComboBox<String> cmbItemCode;

    @FXML
    private JFXComboBox<String> cmbVehicleNo;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblRepairId.setText(generateNextRepairId());
        lblOrderId.setText(generateNextOrderId());
        getVehicleNo();
        getEmployeeId();
        getItemCode();
        setDate();
        setCellValueFactory();
        lblNeeded.setVisible(false);
        btnOrderPlace.setDisable(true);
    }

    private void setCellValueFactory() {
        colVehicleNo.setCellValueFactory(new PropertyValueFactory<>("vehicleNo"));
        colRepairDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colRepairDate.setCellValueFactory(new PropertyValueFactory<>("repairDate"));
        colRepairCost.setCellValueFactory(new PropertyValueFactory<>("repairCost"));
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colIetmQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colItemUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotalPrice.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
        colRemove.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));
    }

    private void setDate() {
        lblDate.setText(String.valueOf(LocalDate.now()));
    }

    private void getItemCode() {
        ObservableList<String> itemCodeList = FXCollections.observableArrayList();

        try {
            ArrayList<ItemDTO> allItems = itemBO.getAllItems();
            for(ItemDTO itemDTO : allItems){
                itemCodeList.add(itemDTO.getCode());
            }
            cmbItemCode.setItems(itemCodeList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void getEmployeeId() {
        ObservableList<String> employeeIds = FXCollections.observableArrayList();
        try {
            ArrayList<EmployeeDTO> allEmployee = employeeBO.getAllEmployee();
            for(EmployeeDTO employeeDTO : allEmployee){
                employeeIds.add(employeeDTO.getId());
            }
            cmbEmployeeId.setItems(employeeIds);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void getVehicleNo() {
        ObservableList<String> vehicleList = FXCollections.observableArrayList();

        try {
            ArrayList<VehicleDTO> allVehicle = vehicleBO.getAllVehicle();
            for(VehicleDTO vehicleDTO : allVehicle ){
               vehicleList.add(vehicleDTO.getVehicleNo());
            }
            cmbVehicleNo.setItems(vehicleList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateNextOrderId() {
        try {
            ResultSet resultSet = placeOrderBO.generateNextOrderId();
            String currentOrderId = null;
            if(resultSet.next()){
                currentOrderId = resultSet.getString(1);
                return nextOrderId(currentOrderId);
            }
            return nextOrderId(currentOrderId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String nextOrderId(String currentOrderId) {
        String nextOrderId=null;
        if (currentOrderId==null){
            nextOrderId="OR001";
        }else {
            String data = currentOrderId.replace("OR", "");
            int num = Integer.parseInt(data);
            num++;

            if (num >= 1 && num <= 9) {
                nextOrderId = "OR00" + num;
            } else if (num >= 10 && num <= 99) {
                nextOrderId = "OR0" + num;
            } else if (num >= 100 && num <= 999) {
                nextOrderId = "OR" + num;
            }
        }
        return nextOrderId;
    }

    private String generateNextRepairId() {
        try {
            ResultSet resultSet = repairBO.generateRepairId();
            String currentRepairId = null;
            if(resultSet.next()){
                currentRepairId = resultSet.getString(1);
                return nextRepairId(currentRepairId);
            }
            return nextRepairId(null);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String nextRepairId(String currentRepairId) {
        String nextRepairId=null;
        if (currentRepairId==null){
            nextRepairId="R001";
        }else {
            String data = currentRepairId.replace("R", "");
            int num = Integer.parseInt(data);
            num++;

            if (num >= 1 && num <= 9) {
                nextRepairId = "R00" + num;
            } else if (num >= 10 && num <= 99) {
                nextRepairId = "R0" + num;
            } else if (num >= 100 && num <= 999) {
                nextRepairId = "R" + num;
            }
        }
        return nextRepairId;
    }

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
