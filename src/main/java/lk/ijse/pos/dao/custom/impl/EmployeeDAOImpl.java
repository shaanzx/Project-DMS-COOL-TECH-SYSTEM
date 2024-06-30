package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.SQLUtil;
import lk.ijse.pos.dao.custom.EmployeeDAO;
import lk.ijse.pos.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public ResultSet generateId() throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT eId FROM employee ORDER BY eId DESC LIMIT 1");
    }

    @Override
    public ArrayList<Employee> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Employee entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Employee entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Employee search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
