package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.PaymentDTO;
import lk.ijse.pos.entity.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentBO extends SuperBO {
    String generatePaymentId() throws SQLException, ClassNotFoundException;

    ArrayList<PaymentDTO> getAllPayment() throws SQLException, ClassNotFoundException;

    boolean savePayment(PaymentDTO dto) throws SQLException, ClassNotFoundException;

    boolean updatePayment(PaymentDTO dto) throws SQLException, ClassNotFoundException;

    boolean deletePayment(String id) throws SQLException, ClassNotFoundException;

    PaymentDTO searchPayment(String id) throws SQLException, ClassNotFoundException;
}
