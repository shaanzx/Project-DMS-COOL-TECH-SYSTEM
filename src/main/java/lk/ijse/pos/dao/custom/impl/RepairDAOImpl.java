package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.SQLUtil;
import lk.ijse.pos.dao.custom.RepairDAO;
import lk.ijse.pos.entity.Repair;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RepairDAOImpl implements RepairDAO {
    @Override
    public int countRepairId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT COUNT(eId) from repair");
        if (resultSet.next()) {
            int idd = Integer.parseInt(resultSet.getString(1));
            return idd;
        }
        return Integer.parseInt(null);
    }

    @Override
    public ResultSet generateId() throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT rId FROM repair ORDER BY rId DESC LIMIT 1");
    }

    @Override
    public ArrayList<Repair> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Repair entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO repair VALUES(?,?,?,?,?,?,?,?)",
                entity.getRepairId(),
                entity.getVehicleNo(),
                entity.getDescription(),
                entity.getRepairDate(),
                entity.getRepairCost(),
                entity.getEmployeeId(),
                entity.getItemCode(),
                entity.getTotalAmount());
    }

    @Override
    public boolean update(Repair entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Repair search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
