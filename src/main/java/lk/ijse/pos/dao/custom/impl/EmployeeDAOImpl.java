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
        ArrayList<Employee> employeeList = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM employee");
        while (resultSet.next()) {
            employeeList.add(new Employee(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            ));
        }
        return employeeList;
    }

    @Override
    public boolean save(Employee entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO employee VALUES(?,?,?,?,?,?)",
                entity.getId(),
                entity.getName(),
                entity.getAddress(),
                entity.getTel(),
                entity.getJobRole(),
                entity.getUserId());
    }

    @Override
    public boolean update(Employee entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE employee SET eName=?, eAddress=?, eTel=?, ejobRole=?, uId=? WHERE eId=?",
                entity.getName(),
                entity.getAddress(),
                entity.getTel(),
                entity.getJobRole(),
                entity.getUserId(),
                entity.getId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM employee WHERE eId = ?",id);
    }

    @Override
    public Employee search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM employee WHERE eId = ?",id);
        Employee employee = null;
        if(resultSet.next()){
            String Empid = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String tel = resultSet.getString(4);
            String jobRole = resultSet.getString(5);
            String userId = resultSet.getString(6);

            employee = new Employee(Empid,name,address,tel,jobRole,userId);
        }
        return employee;
    }
}
