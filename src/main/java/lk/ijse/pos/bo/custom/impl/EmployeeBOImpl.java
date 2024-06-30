package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.EmployeeBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.EmployeeDAO;
import lk.ijse.pos.dto.EmployeeDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {
    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDaoType(DAOFactory.DAOType.EMPLOYEE);

    @Override
    public ResultSet generateId() throws SQLException, ClassNotFoundException {
        return employeeDAO.generateId();
    }

    @Override
    public ArrayList<EmployeeDTO> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public EmployeeDTO search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
