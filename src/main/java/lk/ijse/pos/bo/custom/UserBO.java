package lk.ijse.pos.bo.custom;

import javafx.scene.layout.Pane;
import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dao.SQLUtil;
import lk.ijse.pos.dto.UserDTO;
import lk.ijse.pos.entity.User;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface UserBO extends SuperBO {
    ResultSet checkUserNamePassword(String userName,String Pw) throws SQLException, IOException, ClassNotFoundException;

    ResultSet generateUserId() throws SQLException, ClassNotFoundException;

    List<User> getAllUser() throws SQLException;

    boolean saveUser(UserDTO dto) throws SQLException, ClassNotFoundException;

    boolean updateUser(UserDTO dto) throws SQLException;

    boolean deleteUser(String id) throws SQLException;

    User searchUser(String id) throws SQLException;

    }
