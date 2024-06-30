package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class EmployeeFormController {

    @FXML
    private JFXComboBox<?> cmbUserId;

    @FXML
    private TableColumn<?, ?> colEmpAddress;

    @FXML
    private TableColumn<?, ?> colEmpId;

    @FXML
    private TableColumn<?, ?> colEmpJobRole;

    @FXML
    private TableColumn<?, ?> colEmpName;

    @FXML
    private TableColumn<?, ?> colEmpTel;

    @FXML
    private Label employeeAddressValidate;

    @FXML
    private Label employeeJobRoleValidate;

    @FXML
    private Label employeeNameValidate;

    @FXML
    private Label employeeTelValidate;

    @FXML
    private Pane pagingPane;

    @FXML
    private TableView<?> tblEmployee;

    @FXML
    private Label txtDate;

    @FXML
    private TextField txtEmpAddress;

    @FXML
    private TextField txtEmpId;

    @FXML
    private TextField txtEmpJobRole;

    @FXML
    private TextField txtEmpName;

    @FXML
    private TextField txtEmpTel;

    @FXML
    void btnEmpDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnEmpSaveOnAction(ActionEvent event) {

    }

    @FXML
    void btnEmpUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void tblEmployeeClickOnAction(MouseEvent event) {

    }

    @FXML
    void txtSearchEmployeeOnAction(ActionEvent event) {

    }

}
