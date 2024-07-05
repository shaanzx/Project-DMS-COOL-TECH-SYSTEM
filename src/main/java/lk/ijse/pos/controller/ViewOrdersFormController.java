package lk.ijse.pos.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.ViewOrderBO;
import lk.ijse.pos.dto.ViewOrdersDTO;
import lk.ijse.pos.tm.ViewOrderTm;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ViewOrdersFormController implements Initializable {

    ViewOrderBO viewOrderBO = (ViewOrderBO) BOFactory.getBoFactory().getBOType(BOFactory.BOType.VIEW_ORDER);

    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colItemQty;

    @FXML
    private TableColumn<?, ?> colItemUnitPrice;

    @FXML
    private TableColumn<?, ?> colOrderDate;

    @FXML
    private TableColumn<?, ?> colOrderId;

    @FXML
    private TableColumn<?, ?> colTotalAmount;

    @FXML
    private Pane orderViewPane;

    @FXML
    private TableView<ViewOrderTm> tblOrderView;

    @FXML
    private TextField txtOrderId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        loadOrderDetails();
    }

    private void loadOrderDetails() {
        tblOrderView.getItems().clear();
        try {
            List<ViewOrdersDTO> viewOrders = viewOrderBO.getViewOrders();
            for (ViewOrdersDTO viewOrder : viewOrders) {
                tblOrderView.getItems().add(new ViewOrderTm(
                        viewOrder.getCustomerId(),
                        viewOrder.getOrderId(),
                        viewOrder.getDate(),
                        viewOrder.getItemCode(),
                        viewOrder.getItemQty(),
                        viewOrder.getItemUnitPrice(),
                        viewOrder.getTotalAmount()
                ));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colItemQty.setCellValueFactory(new PropertyValueFactory<>("itemQty"));
        colItemUnitPrice.setCellValueFactory(new PropertyValueFactory<>("itemUnitPrice"));
        colTotalAmount.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        ObservableList<ViewOrderTm> tmOrderList = FXCollections.observableArrayList();

        String orderId = txtOrderId.getText();

        try {
            List<ViewOrdersDTO> viewOrdersByOrderId = viewOrderBO.getViewOrdersByOrderId(orderId);
            for (ViewOrdersDTO viewOrder : viewOrdersByOrderId) {
                tmOrderList.add(new ViewOrderTm(
                        viewOrder.getCustomerId(),
                        viewOrder.getOrderId(),
                        viewOrder.getDate(),
                        viewOrder.getItemCode(),
                        viewOrder.getItemQty(),
                        viewOrder.getItemUnitPrice(),
                        viewOrder.getTotalAmount()
                ));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
