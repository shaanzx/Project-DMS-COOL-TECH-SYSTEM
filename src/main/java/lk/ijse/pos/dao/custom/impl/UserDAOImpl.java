package lk.ijse.pos.dao.custom.impl;

import javafx.scene.layout.Pane;
import lk.ijse.pos.controller.RegisterFormController;
import lk.ijse.pos.dao.SQLUtil;
import lk.ijse.pos.dao.custom.UserDAO;
import lk.ijse.pos.entity.User;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    @Override
    public ResultSet checkUserNamePassword(String userName,String pw) throws SQLException, IOException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT uName, uPassword FROM user WHERE uName = ?");
        return resultSet;
    }

    @Override
    public String generateId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT uId FROM user ORDER BY uId DESC LIMIT 1");
        String currentUserId = null;
        if(resultSet.next()){
            currentUserId = resultSet.getString(1);
            //return RegisterFormController.splitUserId(currentUserId);
        }
        //return RegisterFormController.splitUserId(null);
        return null;
    }

    @Override
    public List<User> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public boolean save(User entity) throws SQLException {
        return false;
    }

    @Override
    public boolean update(User entity) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public User search(String id) throws SQLException {
        return null;
    }
}
