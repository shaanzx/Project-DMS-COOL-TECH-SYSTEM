package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.VehicleDTO;
import lk.ijse.pos.entity.Vehicle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface VehicleBO extends SuperBO {

    ResultSet generateVehicleId() throws SQLException, ClassNotFoundException;

    ArrayList<VehicleDTO> getAllVehicle() throws SQLException, ClassNotFoundException;

    boolean saveVehicle(VehicleDTO dto) throws SQLException, ClassNotFoundException;

    boolean updateVehicle(VehicleDTO dto) throws SQLException, ClassNotFoundException;

    boolean deleteVehicle(String id) throws SQLException, ClassNotFoundException;

    Vehicle searchVehicle(String id) throws SQLException, ClassNotFoundException;
}
