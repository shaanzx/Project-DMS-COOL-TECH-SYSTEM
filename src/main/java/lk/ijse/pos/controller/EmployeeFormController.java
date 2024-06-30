package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.EmployeeBO;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EmployeeFormController  implements Initializable {

    EmployeeBO employeeBO = (EmployeeBO) BOFactory.getBoFactory().getBOType(BOFactory.BOType.EMPLOYEE);

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

    String empId = txtEmpId.getText();
    String empName = txtEmpName.getText();
    String empAddress = txtEmpAddress.getText();
    String tel = txtEmpTel.getText();
    String jobRole = txtEmpJobRole.getText();
    String userId = new NewLoginFormController().uId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generateNextEmployeeId();
        setDate();
        setCellValueFactory();
        loadAllEmployee();
    }

    private String generateNextEmployeeId() {
        try {
            ResultSet resultSet = employeeBO.generateId();
            String currenEmpId = null;
            if(resultSet.next()) {
                currenEmpId = resultSet.getString(1);
                return nextEmpId(currenEmpId);
            }
            return nextEmpId(currenEmpId);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String nextEmpId(String currenEmpId) {
        String next=null;
        if (currenEmpId==null){
            next="E001";
        }else {
            String data = currenEmpId.replace("E","");
            int num = Integer.parseInt(data);
            num++;

            if (num>= 1 && num<= 9){
                next = "E00"+num;
            }else if (num>= 10 && num<= 99){
                next = "E0"+num;
            }else if (num>= 100 && num<= 999){
                next = "E"+num;
            }
        }
        return next;
    }

    private void loadAllEmployee() {

    }

    private void setCellValueFactory() {
        colEmpId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colEmpName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmpAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEmpTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        colEmpJobRole.setCellValueFactory(new PropertyValueFactory<>("jobRole"));
    }

    private void setDate() {
        txtDate.setText(String.valueOf(LocalDate.now()));
    }

    private void cleartextField(){
        txtEmpId.clear();
        txtEmpName.clear();
        txtEmpAddress.clear();
        txtEmpTel.clear();
        txtEmpJobRole.clear();
    }

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
