package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.SQLUtil;
import lk.ijse.pos.dao.custom.PaymentDAO;
import lk.ijse.pos.entity.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public ResultSet generateId() throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT pId FROM payment ORDER BY pId DESC LIMIT 1");
    }

    @Override
    public ArrayList<Payment> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Payment entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO payment VALUES(?,?,?,?,?,?,?,?)",
                entity.getPaymentId(),
                entity.getCustomerId(),
                entity.getOrderId(),
                entity.getRepairId(),
                entity.getTotalAmount(),
                entity.getPaymentDate(),
                entity.getPayCash(),
                entity.getBalance());
    }

    @Override
    public boolean update(Payment entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Payment search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
