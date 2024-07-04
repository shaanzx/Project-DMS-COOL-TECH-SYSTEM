package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.CrudDAO;
import lk.ijse.pos.entity.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CustomerDAO extends CrudDAO<Customer> {
    Customer searchByMobile(String tel) throws SQLException, ClassNotFoundException;

    List<String> getCustomerId() throws SQLException, ClassNotFoundException;
}
