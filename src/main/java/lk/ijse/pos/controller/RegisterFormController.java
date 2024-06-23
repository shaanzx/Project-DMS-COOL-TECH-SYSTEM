package lk.ijse.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.UserBO;

import java.net.URL;
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

    @FXML
    void btnConfirmOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
