package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.CrudDAO;
import lk.ijse.pos.entity.Repair;

import java.sql.SQLException;

public interface RepairDAO extends CrudDAO<Repair> {

    int countRepairId() throws SQLException;
}
