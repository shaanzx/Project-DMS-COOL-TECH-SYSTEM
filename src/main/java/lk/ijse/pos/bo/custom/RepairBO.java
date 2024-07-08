package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dao.SQLUtil;
import lk.ijse.pos.dto.RepairDTO;
import lk.ijse.pos.entity.Repair;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface RepairBO extends SuperBO {

    int countRepairId() throws SQLException;

    ResultSet generateRepairId() throws SQLException, ClassNotFoundException;

    ArrayList<RepairDTO> getAllRepair() throws SQLException, ClassNotFoundException;

    boolean saveRepair(RepairDTO dto) throws SQLException, ClassNotFoundException;

    boolean updateRepair(RepairDTO dto) throws SQLException, ClassNotFoundException;

    boolean deleteRepair(String id) throws SQLException, ClassNotFoundException;

    RepairDTO searchRepair(String id) throws SQLException, ClassNotFoundException;
}
