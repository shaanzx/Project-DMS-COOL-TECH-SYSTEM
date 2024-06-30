package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.EmployeeDTO;
import lk.ijse.pos.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO extends SuperBO {

    ResultSet generateId() throws SQLException, ClassNotFoundException;

    ArrayList<EmployeeDTO> getAll() throws SQLException, ClassNotFoundException;

    boolean save(EmployeeDTO dto) throws SQLException, ClassNotFoundException;

    boolean update(EmployeeDTO dto) throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;

    EmployeeDTO search(String id) throws SQLException, ClassNotFoundException;
}
