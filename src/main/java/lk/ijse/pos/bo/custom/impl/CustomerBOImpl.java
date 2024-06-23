package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.CustomerBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.pos.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDaoType(DAOFactory.DAOType.CUSTOMER);
    @Override
    public ResultSet generateCustomerId() throws SQLException, ClassNotFoundException {
        return customerDAO.generateId();
    }

    @Override
    public List<Customer> getAllCustomer() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public boolean saveCustomer(Customer entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateCustomer(Customer entity) throws SQLException {
        return false;
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException {
        return false;
    }

    @Override
    public Customer searchCustomer(String id) throws SQLException {
        return null;
    }

    @Override
    public Customer searchByMobileCustomer(String tel) throws SQLException {
        return null;
    }
}
