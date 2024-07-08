package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.RepairBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.RepairDAO;
import lk.ijse.pos.dto.RepairDTO;
import lk.ijse.pos.entity.Repair;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RepairBOImpl implements RepairBO {
    RepairDAO repairDAO = (RepairDAO) DAOFactory.getDaoFactory().getDaoType(DAOFactory.DAOType.REPAIR);

    @Override
    public int countRepairId() throws SQLException {
        return 0;
    }

    @Override
    public ResultSet generateRepairId() throws SQLException, ClassNotFoundException {
        return repairDAO.generateId();
    }

    @Override
    public ArrayList<RepairDTO> getAllRepair() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean saveRepair(RepairDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateRepair(RepairDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteRepair(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public RepairDTO searchRepair(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
