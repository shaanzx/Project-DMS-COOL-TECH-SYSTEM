package lk.ijse.pos.bo.custom.impl;


import lk.ijse.pos.bo.custom.VehicleBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.CustomerDAO;
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
        ArrayList<VehicleDTO> vehicleList = new ArrayList<>();
        ArrayList<Vehicle> allVehicles = vehicleDAO.getAll();
        for (Vehicle vehicle : allVehicles) {
            vehicleList.add(new VehicleDTO(
                    vehicle.getVehicleNo(),
                    vehicle.getModel(),
                    vehicle.getType(),
                    vehicle.getCustomerId()
            ));
        }
        return vehicleList;
    }

    @Override
    public boolean saveVehicle(VehicleDTO dto) throws SQLException, ClassNotFoundException {
        return vehicleDAO.save(new Vehicle(
                dto.getVehicleNo(),
                dto.getModel(),
                dto.getType(),
                dto.getCustomerId()
        ));
    }

    @Override
    public boolean updateVehicle(VehicleDTO dto) throws SQLException, ClassNotFoundException {
        return vehicleDAO.update(new Vehicle(
                dto.getVehicleNo(),
                dto.getModel(),
                dto.getType(),
                dto.getCustomerId()
        ));
    }

    @Override
    public boolean deleteVehicle(String vehicleNo) throws SQLException, ClassNotFoundException {
        return vehicleDAO.delete(vehicleNo);
    }

    @Override
    public VehicleDTO searchVehicle(String vehicleNo) throws SQLException, ClassNotFoundException {
        Vehicle vehicle = vehicleDAO.search(vehicleNo);
        return new VehicleDTO(
                vehicle.getVehicleNo(),
                vehicle.getModel(),
                vehicle.getType(),
                vehicle.getCustomerId()
        );
    }

}
