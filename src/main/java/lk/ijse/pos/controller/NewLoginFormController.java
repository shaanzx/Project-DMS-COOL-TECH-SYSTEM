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
import lk.ijse.pos.util.DataValidateController;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NewLoginFormController {

    public AnchorPane mainpane;

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

    public String userId;

    private static NewLoginFormController controller;

    public NewLoginFormController(){
        controller = this;
    }

    public static NewLoginFormController getInstance(){
        return controller;
    }

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

        if(DataValidateController.validateUserName(userName)) {
            userIdValidate.setText("");
            try {
                ResultSet resultSet = userBO.checkUserNamePassword(userName,password);
                while(resultSet.next()){
                    String uId = resultSet.getString(1);
                    String uName = resultSet.getString(2);
                    String uPassword = resultSet.getString(3);

                    if (password.equals(uPassword)) {
                        ancLogin.getScene().getWindow().hide();
                        gotoDashBoard();
                    }else{
                        new Alert(Alert.AlertType.ERROR,"Incorrect Password!").show();
                    }
                }
            } catch (  SQLException  e ) {
                new Alert(Alert.AlertType.ERROR, "User id or password doesn't match.Try aging!").show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }else{
            userIdValidate.setText("Invalid User Id");
        }
    }

    @FXML
    void btnSignUpOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/register_form.fxml"));

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
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/global_new_form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Dashboard Form");
        stage.show();
    }
}
