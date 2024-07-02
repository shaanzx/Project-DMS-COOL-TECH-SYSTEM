package lk.ijse.pos.bo.custom.impl;


import lk.ijse.pos.bo.custom.VehicleBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.VehicleDAO;
import lk.ijse.pos.dto.EmployeeDTO;
import lk.ijse.pos.dto.VehicleDTO;
import lk.ijse.pos.entity.Vehicle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VehicleBOImpl implements VehicleBO {
    VehicleDAO vehicleDAO = (VehicleDAO) DAOFactory.getDaoFactory().getDaoType(DAOFactory.DAOType.VEHICLE);

    @Override
    public ResultSet generateVehicleId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<VehicleDTO> getAllVehicle() throws SQLException, ClassNotFoundException {
        ArrayList<VehicleDTO> vehicles = new ArrayList<>();
        ArrayList<Vehicle> allVehicles = vehicleDAO.getAll();
        for (Vehicle vehicle : allVehicles) {
            vehicles.add(new VehicleDTO(
                    vehicle.getVehicleNo(),
                    vehicle.getModel(),
                    vehicle.getType(),
                    vehicle.getCustomerId()
            ));
        }
        return vehicles;
    }

    @Override
    public boolean saveVehicle(VehicleDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateVehicle(VehicleDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteVehicle(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Vehicle searchVehicle(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
