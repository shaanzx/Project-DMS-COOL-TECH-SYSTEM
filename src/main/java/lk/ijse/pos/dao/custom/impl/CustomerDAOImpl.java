package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.SQLUtil;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CustomerDAOImpl  implements CustomerDAO {

    @Override
    public ResultSet generateId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet =  SQLUtil.execute("SELECT cId FROM customer ORDER BY cId DESC LIMIT 1;");
        return resultSet;
    }

    @Override
    public List<Customer> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public boolean save(Customer entity) throws SQLException, ClassNotFoundException {
       return false;
    }

    @Override
    public boolean update(Customer entity) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public Customer search(String id) throws SQLException {
        return null;
    }

    @Override
    public Customer searchByMobile(String tel) throws SQLException {
        return null;
    }
}
