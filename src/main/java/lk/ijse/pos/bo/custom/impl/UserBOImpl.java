package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.UserBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.dao.custom.UserDAO;
import lk.ijse.pos.dto.UserDTO;
import lk.ijse.pos.entity.User;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserBOImpl implements UserBO {
    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDaoType(DAOFactory.DAOType.USER);

    @Override
    public ResultSet checkUserNamePassword(String userName, String pw) throws SQLException, IOException, ClassNotFoundException {
        return userDAO.checkUserNamePassword(userName,pw);
    }

    @Override
    public ResultSet generateUserId() throws SQLException, ClassNotFoundException {
        return userDAO.generateId();
    }

    @Override
    public List<User> getAllUser() throws SQLException {
        return List.of();
    }

    @Override
    public boolean saveUser(UserDTO dto) throws SQLException, ClassNotFoundException {
        return userDAO.save(new User(dto.getId(),dto.getName(),dto.getPassword()));
    }

    @Override
    public boolean updateUser(UserDTO dto) throws SQLException {
        return false;
    }


    @Override
    public boolean deleteUser(String id) throws SQLException {
        return false;
    }

    @Override
    public User searchUser(String id) throws SQLException {
        return null;
    }
}
