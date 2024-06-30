package lk.ijse.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.ItemBO;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.tm.ItemTm;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ItemFormController implements Initializable {

    ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBOType(BOFactory.BOType.ITEM);

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colItemName;

    @FXML
    private TableColumn<?, ?> colModel;

    @FXML
    private TableColumn<?, ?> colQtyOnHand;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private DatePicker dpDate;

    @FXML
    private Label itemNameValidate;

    @FXML
    private Label itemQtyValidate;

    @FXML
    private Label itemUnitPriceValidate;

    @FXML
    private Label itemVehicleModelValidate;

    @FXML
    private Label lblDateItem;

    @FXML
    private Pane pagingPane;

    @FXML
    private TableView<ItemTm> tblItem;

    @FXML
    private TextField txtItemCode;

    @FXML
    private TextField txtItemName;

    @FXML
    private TextField txtQytOnHand;

    @FXML
    private TextField txtUnitPrice;

    @FXML
    private TextField txtVehicleModel;

    String itemCode = txtItemCode.getText();
    String description = txtItemName.getText();
    String vehicleModel = txtVehicleModel.getText();
    int qytOnHand = Integer.parseInt(txtQytOnHand.getText());
    double unitPrice = Double.parseDouble(txtUnitPrice.getText());
    String date = dpDate.getValue().toString();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setDate();
        setCellValueFactory();
        generateNextItemCode();
        loadAllItem();
    }

    private void loadAllItem() {
        tblItem.getItems().clear();
        try {
            ArrayList<ItemDTO> allItems = itemBO.getAllItems();
            for (ItemDTO itemDTO : allItems) {
                tblItem.getItems().add(new ItemTm(
                        itemDTO.getCode(),
                        itemDTO.getDescription(),
                        itemDTO.getModel(),
                        itemDTO.getQtyOnHand(),
                        itemDTO.getUnitPrice(),
                        itemDTO.getDate()
                ));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateNextItemCode() {
        try {
            ResultSet resultSet = itemBO.generateIdItemCode();
            String currentItemCode = null;
            if(resultSet.next()) {
                currentItemCode = resultSet.getString(1);
                return nextItemCode(currentItemCode);
            }
            return nextItemCode(null);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String nextItemCode(String currentItemCode) {
        String next=null;
        if (currentItemCode==null){
            next="I001";
        }else {
            String data = currentItemCode.replace("I","");
            int num = Integer.parseInt(data);
            num++;

            if (num>= 1 && num<= 9){
                next = "I00"+num;
            }else if (num>= 10 && num<= 99){
                next = "I0"+num;
            }else if (num>= 100 && num<= 999){
                next = "I"+num;
            }
        }
        return next;

    }

    private void setCellValueFactory() {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("description"));
        colModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
    }

    private void setDate() {
        LocalDate now = LocalDate.now();
        lblDateItem.setText(String.valueOf(now));
    }

    @FXML
    void btnItemSaveOnAction(ActionEvent event) {
        try {
            boolean isSaved = itemBO.saveItem(new ItemDTO(itemCode, description, vehicleModel, qytOnHand, unitPrice, date));
            if(isSaved){
                new Alert(Alert.AlertType.INFORMATION, "Item Saved").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnItemDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnItemUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void tblItemClickOnAction(MouseEvent event) {

    }

    @FXML
    void txtSearchItemOnAction(ActionEvent event) {

    }

}
