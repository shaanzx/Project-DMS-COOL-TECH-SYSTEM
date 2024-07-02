package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.VehicleBO;
import lk.ijse.pos.dto.VehicleDTO;
import lk.ijse.pos.tm.VehicleTm;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class VehicleFormController implements Initializable {

    VehicleBO vehicleBO = (VehicleBO) BOFactory.getBoFactory().getBOType(BOFactory.BOType.VEHICLE);

    @FXML
    private JFXComboBox<?> cmbCusId;

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
    String VehicleModel = txtVehicleModel.getText();
    String VehicleType = txtVehicleType.getText();
    String customerId = cmbCusId.getSelectionModel().getSelectedItem().toString();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadAllVehicles();
        setDate();
        generateNextVehicleId();
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void generateNextVehicleId() {
    }

    private void setDate() {

    }

    @FXML
    void btnAddNewCustomerOnAction(ActionEvent event) {

    }

    @FXML
    void btnVehicleDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnVehicleSaveOnAction(ActionEvent event) {

    }

    @FXML
    void btnVehicleUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void cmbCustomerIdOnAction(ActionEvent event) {

    }

    @FXML
    void tblVehicleClickOnAction(MouseEvent event) {

    }

    @FXML
    void txtSearchVehicleOnAction(ActionEvent event) {

    }

}
