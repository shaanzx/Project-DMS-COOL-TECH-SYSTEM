package lk.ijse.pos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RunApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/new_login_form.fxml"))));
        stage.setTitle("Login");
        stage.centerOnScreen();
        stage.show();
    }
    public static void main(String[] args) {launch(args);}
}
