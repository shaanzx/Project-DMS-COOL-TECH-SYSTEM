package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.EmployeeBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.EmployeeDAO;
import lk.ijse.pos.dto.EmployeeDTO;
import lk.ijse.pos.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {
    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDaoType(DAOFactory.DAOType.EMPLOYEE);

    @Override
    public ResultSet generateEmployeeId() throws SQLException, ClassNotFoundException {
        return employeeDAO.generateId();
    }

    @Override
    public ArrayList<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeDTO> employees = new ArrayList<>();
        ArrayList<Employee> employeeArrayList = employeeDAO.getAll();
        for (Employee employee : employeeArrayList) {
            EmployeeDTO employeeDTO = new EmployeeDTO(
                    employee.getId(),
                    employee.getName(),
                    employee.getAddress(),
                    employee.getTel(),
                    employee.getJobRole(),
                    employee.getUserId()
            );
            employees.add(employeeDTO);
        }
        return employees;
    }

    @Override
    public boolean saveEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.save(new Employee(
                dto.getId(),
                dto.getName(),
                dto.getAddress(),
                dto.getTel(),
                dto.getJobRole(),
                dto.getUserId()
        ));
    }

    @Override
    public boolean updateEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(new Employee(
                dto.getId(),
                dto.getName(),
                dto.getAddress(),
                dto.getTel(),
                dto.getJobRole(),
                dto.getUserId()
        ));
    }

    @Override
    public boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(id);
    }

    @Override
    public EmployeeDTO searchEmployee(String id) throws SQLException, ClassNotFoundException {
        Employee employee = employeeDAO.search(id);
        return new EmployeeDTO(
                employee.getId(),
                employee.getName(),
                employee.getAddress(),
                employee.getTel(),
                employee.getJobRole(),
                employee.getUserId()
        );
    }
}
