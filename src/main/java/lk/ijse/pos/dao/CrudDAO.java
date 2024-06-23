package lk.ijse.pos.dao;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO <T> extends SuperDAO {
    String generateId() throws SQLException, ClassNotFoundException;
    List<T> getAll() throws SQLException;
    boolean save(T dto) throws SQLException;
    boolean update(T dto) throws SQLException;
    boolean delete(String id) throws SQLException;
    T search(String id) throws SQLException;
}
