package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.CustomerBO;
import lk.ijse.pos.bo.custom.VehicleBO;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.dto.VehicleDTO;
import lk.ijse.pos.tm.VehicleTm;
import lk.ijse.pos.util.Navigation;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class VehicleFormController implements Initializable {

    VehicleBO vehicleBO = (VehicleBO) BOFactory.getBoFactory().getBOType(BOFactory.BOType.VEHICLE);

    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBOType(BOFactory.BOType.CUSTOMER);

    @FXML
    private JFXComboBox<String> cmbCusId;

    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colVehicleModel;

    @FXML
    private TableColumn<?, ?> colVehicleNo;

    @FXML
    private TableColumn<?, ?> colVehicleType;

    @FXML
    private Label lblCustomerName;

    @FXML
    private Pane pagingPane;

    @FXML
    private TableView<VehicleTm> tblVehicle;

    @FXML
    private Label txtDate;

    @FXML
    private TextField txtVehicleModel;

    @FXML
    private TextField txtVehicleNo;

    @FXML
    private TextField txtVehicleType;

    @FXML
    private Label vehicleModelValidate;

    @FXML
    private Label vehicleNoValidate;

    @FXML
    private Label vehicleTypeValidate;

    String vehicleNo = txtVehicleNo.getText();
    String vehicleModel = txtVehicleModel.getText();
    String vehicleType = txtVehicleType.getText();
    String customerId = cmbCusId.getSelectionModel().getSelectedItem().toString();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadAllVehicles();
        setDate();
        generateNextVehicleId();
        setCellValueFactory();
        getCMBCustomerId();
    }

    private void getCMBCustomerId() {
        ObservableList<String> customerList = FXCollections.observableArrayList();
        try {
            List<String> idList = customerBO.getCustomerId();
            for(String id : idList){
                customerList.add(id);
            }
            cmbCusId.setItems(customerList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colVehicleNo.setCellValueFactory(new PropertyValueFactory<>("vehicleNo"));
        colVehicleModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        colVehicleType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
    }

    private void loadAllVehicles() {
        tblVehicle.getItems().clear();
        try {
            ArrayList<VehicleDTO> allVehicle = vehicleBO.getAllVehicle();
            for(VehicleDTO vehicleDTO : allVehicle) {
                tblVehicle.getItems().add(new VehicleTm(
                        vehicleDTO.getVehicleNo(),
                        vehicleDTO.getModel(),
                        vehicleDTO.getType(),
                        vehicleDTO.getCustomerId()
                ));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void generateNextVehicleId() {
    }

    private void setDate() {
        txtDate.setText(String.valueOf(LocalDate.now()));
    }

    private void clearTextField(){
        txtVehicleNo.clear();
        txtVehicleModel.clear();
        txtVehicleType.clear();
        cmbCusId.getSelectionModel().clearSelection();
        lblCustomerName.setText("");
    }

    @FXML
    void btnAddNewCustomerOnAction(ActionEvent event) {
        try {
            Navigation.switchPaging(pagingPane,"customer_form.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnVehicleDeleteOnAction(ActionEvent event) {
        try {
            boolean isDeleted = vehicleBO.deleteVehicle(vehicleNo);
            if(isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION,"Vehicle details deleted!");
                loadAllVehicles();
                clearTextField();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnVehicleSaveOnAction(ActionEvent event) {
        try {
            boolean isSaved = vehicleBO.saveVehicle(new VehicleDTO(vehicleNo, vehicleModel, vehicleType, customerId));
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION,"Vehicle Details Saved!");
                loadAllVehicles();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnVehicleUpdateOnAction(ActionEvent event) {
        try {
            boolean isUpdated = vehicleBO.updateVehicle(new VehicleDTO(vehicleNo, vehicleModel, vehicleType, customerId));
            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION,"Vehicle Details Updated!");
                loadAllVehicles();
                clearTextField();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void cmbCustomerIdOnAction(ActionEvent event) {
        try {
            CustomerDTO customerDTO = customerBO.searchCustomer(customerId);
            lblCustomerName.setText(customerDTO.getName());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void tblVehicleClickOnAction(MouseEvent event) {
        TablePosition tp = tblVehicle.getSelectionModel().getSelectedCells().get(0);
        int row = tp.getRow();
        ObservableList<TableColumn<VehicleTm,?> > columns = tblVehicle.getColumns();

        txtVehicleNo.setText(columns.get(0).getCellData(row).toString());
        txtVehicleModel.setText(columns.get(1).getCellData(row).toString());
        txtVehicleType.setText(columns.get(2).getCellData(row).toString());
        //cmbCusId.setValue(columns.get(3).getCellData(row).toString());

    }

    @FXML
    void txtSearchVehicleOnAction(ActionEvent event) {
        try {
            VehicleDTO vehicleDTO = vehicleBO.searchVehicle(vehicleNo);
            if(vehicleDTO != null){
                txtVehicleNo.setText(vehicleDTO.getVehicleNo());
                txtVehicleModel.setText(vehicleDTO.getModel());
                txtVehicleType.setText(vehicleDTO.getType());
                cmbCusId.getSelectionModel().clearSelection();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
