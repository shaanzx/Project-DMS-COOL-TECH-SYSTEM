package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.dao.custom.OrderDAO;
import lk.ijse.pos.dao.custom.OrderDetailsDAO;
import lk.ijse.pos.dao.custom.impl.OrderDAOImpl;
import lk.ijse.pos.dto.OrderDTO;
import lk.ijse.pos.dto.OrderPlaceDTO;
import lk.ijse.pos.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface PlaceOrderBO extends SuperBO {

    ResultSet generateNextOrderId() throws SQLException, ClassNotFoundException;

    ArrayList<OrderDTO> getAllOrder() throws SQLException, ClassNotFoundException;

    boolean saveOrder(OrderDTO dto) throws SQLException, ClassNotFoundException;

    boolean updateOrder(OrderDTO dto) throws SQLException, ClassNotFoundException;

    boolean deleteOrder(String id) throws SQLException, ClassNotFoundException;

    OrderDTO searchOrder(String id) throws SQLException, ClassNotFoundException;

    boolean orderPlace(OrderPlaceDTO orderPlacedto) throws SQLException;

}
