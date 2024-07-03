package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.CustomerBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDaoType(DAOFactory.DAOType.CUSTOMER);

    @Override
    public ResultSet generateCustomerId() throws SQLException, ClassNotFoundException {
        return customerDAO.generateId();
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> customerDTOArrayList = new ArrayList<>();
        ArrayList<Customer> customers  = customerDAO.getAll();
        for (Customer customer : customers) {
            CustomerDTO customerDTO = new CustomerDTO(
                    customer.getId(), customer.getName(), customer.getAddress(), customer.getTel(), customer.getEmail(), customer.getUserId());
            customerDTOArrayList.add(customerDTO);
        }
        return customerDTOArrayList;
    }

    @Override
    public boolean saveCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.save(new Customer(dto.getId(), dto.getName(), dto.getAddress(), dto.getTel(), dto.getEmail(), dto.getUserId()));
    }

    @Override
    public boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(dto.getId(), dto.getName(), dto.getAddress(), dto.getTel(), dto.getEmail(), dto.getUserId()));
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }

    @Override
    public CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException {
        Customer customer = customerDAO.search(id);
        return new CustomerDTO(
                customer.getId(),
                customer.getName(),
                customer.getAddress(),
                customer.getTel(),
                customer.getEmail(),
                customer.getUserId());
    }

    @Override
    public List<String> getCustomerId() throws SQLException, ClassNotFoundException {
        return customerDAO.getCustomerId();
    }
}
