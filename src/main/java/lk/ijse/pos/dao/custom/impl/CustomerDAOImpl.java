package lk.ijse.pos.dao.custom.impl;

import com.mysql.cj.xdevapi.SqlMultiResult;
import lk.ijse.pos.dao.SQLUtil;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.entity.Customer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CustomerDAOImpl  implements CustomerDAO {

    @Override
    public String generateId() throws SQLException, ClassNotFoundException {
        ResultSet rst =  SQLUtil.execute("SELECT cId FROM customer ORDER BY cId DESC LIMIT 1;");
        String currentCustomerId = null;
        if(rst.next()){
            currentCustomerId = rst.getString(1);
            return nextId(currentCustomerId);
        }
        return nextId(null);
    }

    @Override
    public String nextId(String currentId) {
        return "";
    }

    @Override
    public List<Customer> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public boolean save(Customer dto) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Customer dto) throws SQLException {
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
