package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.SQLUtil;
import lk.ijse.pos.dao.custom.VehicleDAO;
import lk.ijse.pos.entity.Vehicle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VehicleDAOImpl implements VehicleDAO {
    @Override
    public ResultSet generateId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<Vehicle> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Vehicle> vehicleList = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute("select * from vehicle");
        while (resultSet.next()) {
            vehicleList.add(new Vehicle(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            ));
        }
        return vehicleList;
    }

    @Override
    public boolean save(Vehicle entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO vehicle VALUES(?,?,?,?)",
                entity.getVehicleNo(),
                entity.getModel(),
                entity.getType(),
                entity.getCustomerId());
    }

    @Override
    public boolean update(Vehicle entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE vehicle SET vModel=?, vType=?, cid=? WHERE vNo=?",
                entity.getModel(),
                entity.getType(),
                entity.getCustomerId(),
                entity.getVehicleNo());
    }

    @Override
    public boolean delete(String vehicleNo) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM vehicle WHERE vNo = ?",vehicleNo);
    }

    @Override
    public Vehicle search(String vehicleNo) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM vehicle WHERE vNo = ?",vehicleNo);
        Vehicle vehicle = null;
        if(resultSet.next()){
            String vNo = resultSet.getString(1);
            String model = resultSet.getString(2);
            String type = resultSet.getString(3);
            String customerId = resultSet.getString(4);

            vehicle = new Vehicle(vNo,model,type,customerId);
        }
        return vehicle;
    }
}
