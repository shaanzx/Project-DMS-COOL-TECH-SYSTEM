package lk.ijse.pos.dao.custom;

import javafx.scene.layout.Pane;
import lk.ijse.pos.dao.CrudDAO;
import lk.ijse.pos.entity.User;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface UserDAO extends CrudDAO <User> {
    ResultSet checkUserNamePassword(String userName, String pw) throws SQLException, IOException, ClassNotFoundException;
    }
