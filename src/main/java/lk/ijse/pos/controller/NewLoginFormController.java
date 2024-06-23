package lk.ijse.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.UserBO;
import lk.ijse.pos.dao.custom.DashBoardDAO;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NewLoginFormController {
    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBOType(BOFactory.BOType.USER);
    @FXML
    private Pane ancLogin;

    @FXML
    private Label passwordValidate;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private CheckBox txtSave;

    @FXML
    private TextField txtUserId;

    @FXML
    private Label userIdValidate;

    @FXML
    void btnForgetOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveToDeviceOnAction(ActionEvent event) {

    }

    @FXML
    void btnSignInAction(ActionEvent event) {
        String userName = txtUserId.getText();
        String password = txtPassword.getText();
        try {
            ResultSet resultSet = userBO.checkUserNamePassword(userName, password);
            if(resultSet.next()){
                String userId = resultSet.getString(1);
                String userPassword = resultSet.getString(2);
                if(userId.equals(userName) && userPassword.equals(password)){
                    ancLogin.getScene().getWindow().hide();
                    gotoDashBoard();
                }else{
                    new Alert(Alert.AlertType.ERROR,"Incorrect Password!");
                }
            }else{
                new Alert(Alert.AlertType.ERROR,"Incorrect user ID.Check and try again.").show();
            }
        } catch (SQLException | IOException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "User Id Or password doesn't match.Try aging!").show();
        }
    }

    @FXML
    void btnSignUpOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/lk.ijse.pos/view/register_form.fxml"));

        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Register Form");
        stage.show();
    }

    @FXML
    void txtPasswordOnAction(ActionEvent event) {
        btnSignInAction(event);
    }

    @FXML
    void txtUserIdOnAction(ActionEvent event) {
        txtPassword.requestFocus();
    }

    public void gotoDashBoard() throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/global_new_form.fxml"));

        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.centerOnScreen();
        //  stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("DashBoard Form");
        stage.show();
    }

}
