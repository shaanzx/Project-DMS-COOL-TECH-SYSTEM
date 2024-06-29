package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.SQLUtil;
import lk.ijse.pos.dao.custom.UserDAO;
import lk.ijse.pos.entity.User;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class  UserDAOImpl implements UserDAO {

    @Override
    public ResultSet checkUserNamePassword(String userName, String pw) throws SQLException, ClassNotFoundException {

    return SQLUtil.execute("SELECT uId, uName, uPassword FROM user WHERE uName = ?", userName);

}
   /* public boolean checkUserNamePassword(String userName, String pw) throws SQLException, ClassNotFoundException {
        ResultSet resultSet =SQLUtil.execute("SELECT  uId ,uName, uPassword FROM user WHERE uName = ?",userName,pw);
        if(resultSet.next()){
             if (pw.equals(resultSet.getString(3))) {
                 System.out.println(resultSet.getString(1));
                 return true;
            }
        }
        return false;
    }*/



    @Override
    public ResultSet generateId() throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT uId FROM user ORDER BY uId DESC LIMIT 1");
    }

    @Override
    public List<User> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public boolean save(User entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO user VALUES(?, ?, ?)",entity.getId(),entity.getName(),entity.getPassword());
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
