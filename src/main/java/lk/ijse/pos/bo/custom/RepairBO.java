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

    ResultSet generateId() throws SQLException, ClassNotFoundException;

    ArrayList<RepairDTO> getAll() throws SQLException, ClassNotFoundException;

    boolean save(RepairDTO dto) throws SQLException, ClassNotFoundException;

    boolean update(RepairDTO dto) throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;

    RepairDTO search(String id) throws SQLException, ClassNotFoundException;
}
