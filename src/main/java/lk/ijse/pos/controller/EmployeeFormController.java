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
import lk.ijse.pos.bo.custom.EmployeeBO;
import lk.ijse.pos.dto.EmployeeDTO;
import lk.ijse.pos.tm.EmployeeTm;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
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
    private TableView<EmployeeTm> tblEmployee;

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
            ResultSet resultSet = employeeBO.generateEmployeeId();
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
        tblEmployee.getItems().clear();
        try {
            ArrayList<EmployeeDTO> allEmployee = employeeBO.getAllEmployee();
            for (EmployeeDTO employeeDTO : allEmployee) {
                tblEmployee.getItems().add(new EmployeeTm(
                        employeeDTO.getId(),
                        employeeDTO.getName(),
                        employeeDTO.getAddress(),
                        employeeDTO.getTel(),
                        employeeDTO.getJobRole()));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
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
        try {
            boolean isDeleted = employeeBO.deleteEmployee(empId);
            if(isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION,"Deleted!");
                loadAllEmployee();
                cleartextField();
                generateNextEmployeeId();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnEmpSaveOnAction(ActionEvent event) {
        try {
            boolean isSaved = employeeBO.saveEmployee(new EmployeeDTO(empId, empName, empAddress, tel, jobRole, userId));
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Saved").show();
                loadAllEmployee();
                cleartextField();
                generateNextEmployeeId();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnEmpUpdateOnAction(ActionEvent event) {
        try {
            boolean isUpdated = employeeBO.updateEmployee(new EmployeeDTO(empId, empName, empAddress, tel, jobRole, userId));
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated").show();
                loadAllEmployee();
                cleartextField();
                generateNextEmployeeId();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void tblEmployeeClickOnAction(MouseEvent event) {
        TablePosition tp = tblEmployee.getSelectionModel().getSelectedCells().get(0);
        int row = tp.getRow();
        ObservableList<TableColumn<EmployeeTm, ?> > columns = tblEmployee.getColumns();
        txtEmpId.setText(columns.get(0).getCellData(row).toString());
        txtEmpName.setText(columns.get(1).getCellData(row).toString());
        txtEmpAddress.setText(columns.get(2).getCellData(row).toString());
        txtEmpTel.setText(columns.get(3).getCellData(row).toString());
        txtEmpJobRole.setText(columns.get(4).getCellData(row).toString());
        tblEmployee.setCursor(Cursor.HAND);
    }

    @FXML
    void txtSearchEmployeeOnAction(ActionEvent event) {
        try {
            EmployeeDTO employeeDTO = employeeBO.searchEmployee(empId);
            if (employeeDTO != null) {
                txtEmpId.setText(employeeDTO.getId());
                txtEmpName.setText(employeeDTO.getName());
                txtEmpAddress.setText(employeeDTO.getAddress());
                txtEmpTel.setText(employeeDTO.getTel());
                txtEmpJobRole.setText(employeeDTO.getJobRole());
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
