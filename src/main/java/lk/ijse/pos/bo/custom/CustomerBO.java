package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dao.SQLUtil;
import lk.ijse.pos.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CustomerBO extends SuperBO {
    ResultSet generateCustomerId() throws SQLException, ClassNotFoundException;

    List<Customer> getAllCustomer() throws SQLException, ClassNotFoundException;

    boolean saveCustomer(Customer entity) throws SQLException, ClassNotFoundException;

    boolean updateCustomer(Customer entity) throws SQLException;

     boolean deleteCustomer(String id) throws SQLException;

    Customer searchCustomer(String id) throws SQLException;

    Customer searchByMobileCustomer(String tel) throws SQLException;
}
