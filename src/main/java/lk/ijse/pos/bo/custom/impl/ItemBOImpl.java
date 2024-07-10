package lk.ijse.pos.bo.custom.impl;

import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import lk.ijse.pos.bo.custom.ItemBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.dao.custom.impl.ItemDAOImpl;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemBOImpl implements ItemBO {

    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDaoType(DAOFactory.DAOType.ITEM);
    @Override
    public ObservableList<XYChart.Series<String, Integer>> getDataToBarChart() throws SQLException, ClassNotFoundException {
        return itemDAO.getDataToBarChart();
    }

    @Override
    public ResultSet generateIdItemCode() throws SQLException, ClassNotFoundException {
        return itemDAO.generateId();
    }

    @Override
    public ArrayList <ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        ArrayList <ItemDTO> itemDTOS = new ArrayList <>();
        ArrayList<Item> items = itemDAO.getAll();
        for (Item item : items) {
            ItemDTO itemDTO = new ItemDTO(
                    item.getCode(),item.getDescription(),item.getModel(),item.getQtyOnHand(),item.getUnitPrice(),item.getDate());
            itemDTOS.add(itemDTO);
        }
        return itemDTOS;
    }

    @Override
    public boolean saveItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.save(new Item(
                dto.getCode(),
                dto.getDescription(),
                dto.getModel(),
                dto.getQtyOnHand(),
                dto.getUnitPrice(),
                dto.getDate()
        ));
    }

    @Override
    public boolean updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.update(new Item(
                dto.getCode(),
                dto.getDescription(),
                dto.getModel(),
                dto.getQtyOnHand(),
                dto.getUnitPrice(),
                dto.getDate()
        ));
    }

    @Override
    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(id);
    }

    @Override
    public ItemDTO searchItem(String id) throws SQLException, ClassNotFoundException {
        Item item = itemDAO.search(id);
        return new ItemDTO(
                item.getCode(),
                item.getDescription(),
                item.getModel(),
                item.getQtyOnHand(),
                item.getUnitPrice(),
                item.getDate()
        );
    }

    @Override
    public List<String> getItemCodes() throws SQLException, ClassNotFoundException {
        return itemDAO.getItemCodes();
    }
}
