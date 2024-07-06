package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.CustomerBO;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.tm.CustomerTm;
import lk.ijse.pos.util.Navigation;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CustomerFormController implements Initializable {

    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBOType(BOFactory.BOType.CUSTOMER);
    @FXML
    private JFXComboBox<?> cmbUserId;

    @FXML
    private TableColumn<?, ?> colCustomerAddress;

    @FXML
    private TableColumn<?, ?> colCustomerEmail;

    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colCustomerName;

    @FXML
    private TableColumn<?, ?> colCustomerTel;

    @FXML
    private Label customerAddressValidate;

    @FXML
    private Label customerEmailValidate;

    @FXML
    private Label customerNameValidate;

    @FXML
    private Label customerTelValidate;

    @FXML
    private Label lblDate;

    @FXML
    private Pane pagingPane;

    @FXML
    private TableView<CustomerTm> tblCustomer;

    @FXML
    private TextField txtCusAddress;

    @FXML
    private TextField txtCusEmail;

    @FXML
    private TextField txtCusId;

    @FXML
    private TextField txtCusName;

    @FXML
    private TextField txtCusTel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtCusId.setText(generateNewCustomerId());
        loadAllCustomer();
        setCellValueFactory();
        setDate();
    }

    private String generateNewCustomerId() {
        try {
            ResultSet resultSet = customerBO.generateCustomerId();
            String currentCustomerId = null;
            if (resultSet.next()) {
                currentCustomerId = resultSet.getString(1);
                return nextCustomerId(currentCustomerId);
            }
            return nextCustomerId(null);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String nextCustomerId(String currentCustomerId) {
        String next=null;
        if (currentCustomerId==null){
            next="C001";
        }else {
            String data = currentCustomerId.replace("C","");
            int num = Integer.parseInt(data);
            num++;

            if (num>= 1 && num<= 9){
                next = "C00"+num;
            }else if (num>= 10 && num<= 99){
                next = "C0"+num;
            }else if (num>= 100 && num<= 999){
                next = "C"+num;
            }
        }
        return next;
    }

    private void setCellValueFactory() {
            colCustomerId.setCellValueFactory(new PropertyValueFactory<>("id"));
            colCustomerName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colCustomerAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
            colCustomerTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
            colCustomerEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

    }

    private void loadAllCustomer() {
        tblCustomer.getItems().clear();
        try {
            ArrayList<CustomerDTO> allCustomer = customerBO.getAllCustomer();
            for ( CustomerDTO customer : allCustomer ) {
                tblCustomer.getItems().add(new CustomerTm(
                        customer.getId(),
                        customer.getName(),
                        customer.getAddress(),
                        customer.getTel(),
                        customer.getEmail()));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setDate() {
        LocalDate now = LocalDate.now();
        lblDate.setText(String.valueOf(now));
    }

    private void clearTextFields() {
        txtCusId.clear();
        txtCusName.clear();
        txtCusAddress.clear();
        txtCusTel.clear();
        txtCusEmail.clear();
    }

    @FXML
    void btnAddVehicleOnAction(ActionEvent event) {
        try {
            Navigation.switchPaging(pagingPane,"vehicle_form.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnCusDeleteOnAction(ActionEvent event) {
        String cusId = txtCusId.getText();

        try {
            boolean isDeleted = customerBO.deleteCustomer(cusId);
            if(isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Customer is Deleted").show();
                loadAllCustomer();
                clearTextFields();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnCusSaveOnAction(ActionEvent event) {
/*        String cusId = txtCusId.getText();
        String cusName = txtCusName.getText();
        String cusAddress = txtCusAddress.getText();
        String cusTel = txtCusTel.getText();
        String cusEmail = txtCusEmail.getText();
        String userId = new NewLoginFormController().uId;*/

        String cusId = txtCusId.getText();
        String cusName = txtCusName.getText();
        String cusAddress = txtCusAddress.getText();
        String cusTel = txtCusTel.getText();
        String cusEmail = txtCusEmail.getText();
        String userId = new NewLoginFormController().uId;
        try {
            boolean isSaved = customerBO.saveCustomer(new CustomerDTO(cusId, cusName, cusAddress, cusTel, cusEmail, userId));
            if(isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "Customer is Saved").show();
                tblCustomer.getItems().add(new CustomerTm(cusId,cusName,cusAddress,cusTel,cusEmail));
                tblCustomer.refresh();
                clearTextFields();
                generateNewCustomerId();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to save the customer " + e.getMessage()).show();
        }
    }

    @FXML
    void btnCusUpdateOnAction(ActionEvent event) {
        String cusId = txtCusId.getText();
        String cusName = txtCusName.getText();
        String cusAddress = txtCusAddress.getText();
        String cusTel = txtCusTel.getText();
        String cusEmail = txtCusEmail.getText();
        String userId = new NewLoginFormController().uId;

        try {
            boolean isUpdated = customerBO.updateCustomer(new CustomerDTO(cusId, cusName, cusAddress, cusTel, cusEmail, userId));
            if(isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "Customer is Updated").show();
                loadAllCustomer();
                clearTextFields();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to update the customer " + e.getMessage()).show();
        }
    }

    @FXML
    void cmbUserIdOnAction(ActionEvent event) {

    }

    @FXML
    void tblCustomerClick(MouseEvent event) {
        TablePosition tp = tblCustomer.getSelectionModel().getSelectedCells().get(0);
        int row = tp.getRow();
        ObservableList<TableColumn<CustomerTm,?> > columns = tblCustomer.getColumns();

        txtCusId.setText(columns.get(0).getCellData(row).toString());
        txtCusName.setText(columns.get(1).getCellData(row).toString());
        txtCusAddress.setText(columns.get(2).getCellData(row).toString());
        txtCusTel.setText(columns.get(3).getCellData(row).toString());
        txtCusEmail.setText(columns.get(4).getCellData(row).toString());
        tblCustomer.setCursor(Cursor.HAND);
    }

    @FXML
    void txtSearchCustomerOnAction(ActionEvent event) {
        String cusId = txtCusId.getText();

        try {
            CustomerDTO customerDTO = customerBO.searchCustomer(cusId);
            if(customerDTO!=null){
                txtCusId.setText(customerDTO.getId());
                txtCusName.setText(customerDTO.getName());
                txtCusAddress.setText(customerDTO.getAddress());
                txtCusTel.setText(customerDTO.getTel());
                txtCusEmail.setText(customerDTO.getEmail());
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
