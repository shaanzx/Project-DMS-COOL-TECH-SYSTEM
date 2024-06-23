package lk.ijse.pos.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface CrudDAO <T> extends SuperDAO {
    ResultSet generateId() throws SQLException, ClassNotFoundException;
    List<T> getAll() throws SQLException;
    boolean save(T entity) throws SQLException, ClassNotFoundException;
    boolean update(T entity) throws SQLException;
    boolean delete(String id) throws SQLException;
    T search(String id) throws SQLException;
}
