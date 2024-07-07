package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.SQLUtil;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl  implements CustomerDAO {

    @Override
    public ResultSet generateId() throws SQLException, ClassNotFoundException {
        return  SQLUtil.execute("SELECT cId FROM customer ORDER BY cId DESC LIMIT 1;");
    }

    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> customerList = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM customer");
        while (resultSet.next()) {
              customerList.add(new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
              ));
        }
        return customerList;
    }

    @Override
    public boolean save(Customer entity) throws SQLException, ClassNotFoundException {
       return SQLUtil.execute("INSERT INTO customer VALUES(?,?,?,?,?,?)",
               entity.getId(),
               entity.getName(),
               entity.getAddress(),
               entity.getTel(),
               entity.getEmail(),
               entity.getUserId());
    }

    @Override
    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE customer SET name=?, address=?, tel=?, email=?, uId=? WHERE cId=?",
                entity.getName(),
                entity.getAddress(),
                entity.getTel(),
                entity.getEmail(),
                entity.getUserId(),
                entity.getId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM customer WHERE cId = ?",id);
    }

    @Override
    public Customer search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM customer WHERE cId = ?",id);
        Customer customer = null;

        if (resultSet.next()) {
            String cusId = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String tel = resultSet.getString(4);
            String email = resultSet.getString(5);
            String userId = resultSet.getString(6);
            customer = new Customer(cusId, name, address, tel, email, userId);
        }
        return customer;
    }

    @Override
    public Customer searchByMobile(String tel) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM customer WHERE tel = ?",tel);
        Customer customer = null;
        if (resultSet.next()) {
            String cusId = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String telephone = resultSet.getString(4);
            String email = resultSet.getString(5);
            String userId = resultSet.getString(6);
            customer = new Customer(cusId, name, address, telephone, email, userId);

        }
        return customer;
        
    }

    @Override
    public List<String> getCustomerId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT cId FROM customer");

        List<String> customerIdList = new ArrayList<>();
        while (resultSet.next()) {
            customerIdList.add(resultSet.getString(1));
        }
        return customerIdList;
    }
}
