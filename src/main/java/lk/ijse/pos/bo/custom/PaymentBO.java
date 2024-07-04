package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.PaymentDTO;
import lk.ijse.pos.entity.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentBO extends SuperBO {
    String generateId() throws SQLException, ClassNotFoundException;

    ArrayList<PaymentDTO> getAll() throws SQLException, ClassNotFoundException;

    boolean save(PaymentDTO dto) throws SQLException, ClassNotFoundException;

    boolean update(PaymentDTO dto) throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;

    PaymentDTO search(String id) throws SQLException, ClassNotFoundException;
}
