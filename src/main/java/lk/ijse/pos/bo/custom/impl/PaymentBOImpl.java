package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.PaymentBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.impl.PaymentDAOImpl;
import lk.ijse.pos.dto.PaymentDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentBOImpl implements PaymentBO {
    PaymentDAOImpl paymentDAO = (PaymentDAOImpl) DAOFactory.getDaoFactory().getDaoType(DAOFactory.DAOType.PAYMENT);

    @Override
    public String generatePaymentId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = paymentDAO.generateId();
        String currentPaymentId = null;

        if (resultSet.next()) {
            currentPaymentId = resultSet.getString(1);
            return nextPaymentId(currentPaymentId);
        }
        return nextPaymentId(null);
    }

    private String nextPaymentId(String currentPaymentId) {
        String next = null;
        if (currentPaymentId == null) {
            next = "P001";
        } else {
            String data = currentPaymentId.replace("P", "");
            int num = Integer.parseInt(data);
            num++;

            if (num >= 1 && num <= 9) {
                next = "P00" + num;
            } else if (num >= 10 && num <= 99) {
                next = "P0" + num;
            } else if (num >= 100 && num <= 999) {
                next = "P" + num;
            }
        }
        return next;
    }

    @Override
    public ArrayList<PaymentDTO> getAllPayment() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean savePayment(PaymentDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updatePayment(PaymentDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deletePayment(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public PaymentDTO searchPayment(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
