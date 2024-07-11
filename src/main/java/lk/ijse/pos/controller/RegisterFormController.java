package lk.ijse.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.UserBO;
import lk.ijse.pos.dto.UserDTO;
import lk.ijse.pos.util.DataValidateController;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RegisterFormController implements Initializable {
    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBOType(BOFactory.BOType.USER);

    @FXML
    private Label passwordValidate;

    @FXML
    private AnchorPane registerPane;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUserId;

    @FXML
    private TextField txtUserName;

    @FXML
    private Label userNameValidate;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtUserId.setText(generateUserId());
        txtUserId.setDisable(true);
    }

    @FXML
    void btnConfirmOnAction(ActionEvent event) {
        String userId = txtUserId.getText();
        String userName = txtUserName.getText();
        String password = txtPassword.getText();

        if(DataValidateController.validateUserName(txtUserName.getText())) {
            userNameValidate.setText("");
            if (DataValidateController.validateUserPassword(txtPassword.getText())) {
                passwordValidate.setText("");
                try {
                    boolean isSaved = userBO.saveUser(new UserDTO(userId, userName, password));
                    if(isSaved){
                        new Alert(Alert.AlertType.CONFIRMATION, "Successfully Saved!").show();
                        txtUserId.clear();
                        txtUserName.clear();
                        txtPassword.clear();
                        txtUserName.requestFocus();
                    }
                } catch (SQLException | ClassNotFoundException e) {
                    new Alert(Alert.AlertType.ERROR, "Already exists").show();
                }
            } else {
                passwordValidate.setText("Input any character and try again!");
            }
        }else{
            userNameValidate.setText("Invalid user name and try again!");
        }
    }

    public String generateUserId(){
        try {
            ResultSet resultSet = userBO.generateUserId();
            String currentUserId = null;
            if(resultSet.next()){
                currentUserId = resultSet.getString(1);
                return splitUserId(currentUserId);
            }
            return splitUserId(null);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static String splitUserId(String currentUserId) throws SQLException, ClassNotFoundException {
        String next=null;
        if (currentUserId==null){
            next="U001";
        }else {
            String data = currentUserId.replace("U","");
            int num = Integer.parseInt(data);
            num++;

            if (num>= 1 && num<= 9){
                next = "U00"+num;
            }else if (num>= 10 && num<= 99){
                next = "U0"+num;
            }else if (num>= 100 && num<= 999){
                next = "U"+num;
            }
        }
        return next;
    }
}
