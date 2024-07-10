package lk.ijse.pos.dao.custom;

import javafx.scene.chart.PieChart;
import lk.ijse.pos.dao.CrudDAO;
import lk.ijse.pos.dao.SuperDAO;
import lk.ijse.pos.entity.OrderDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDetailsDAO extends CrudDAO<OrderDetails> {

    boolean save(ArrayList<OrderDetails> orderDetailsList) throws SQLException, ClassNotFoundException;
}
