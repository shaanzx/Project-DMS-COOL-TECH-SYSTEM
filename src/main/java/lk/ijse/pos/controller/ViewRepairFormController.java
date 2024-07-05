package lk.ijse.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.OrderBO;
import lk.ijse.pos.bo.custom.RepairBO;
import lk.ijse.pos.dto.ViewRepairDTO;
import lk.ijse.pos.tm.ViewRepairTm;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ViewRepairFormController implements Initializable {
    OrderBO orderBO = (OrderBO) BOFactory.getBoFactory().getBOType(BOFactory.BOType.VIEW_REPAIR);

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colItemQty;

    @FXML
    private TableColumn<?, ?> colItemUnitPrice;

    @FXML
    private TableColumn<?, ?> colRepairCost;

    @FXML
    private TableColumn<?, ?> colRepairDate;

    @FXML
    private TableColumn<?, ?> colRepairId;

    @FXML
    private TableColumn<?, ?> colTotalAmount;

    @FXML
    private TableColumn<?, ?> colVehicleNo;

    @FXML
    private Pane orderViewPane;

    @FXML
    private TableView<ViewRepairTm> tblRepairDetailsTable;

    @FXML
    private TextField txtRepairId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        loadAllRepair();
    }

    private void loadAllRepair() {
        tblRepairDetailsTable.getItems().clear();
        try {
            List<ViewRepairDTO> viewRepair = orderBO.getViewRepair();
            for (ViewRepairDTO viewRepairDTO : viewRepair) {
                tblRepairDetailsTable.getItems().add(new ViewRepairTm(
                        viewRepairDTO.getRepairId(),
                        viewRepairDTO.getVehicleNo(),
                        viewRepairDTO.getRepairDate(),
                        viewRepairDTO.getItemCode(),
                        viewRepairDTO.getItemQty(),
                        viewRepairDTO.getItemUnitPrice(),
                        viewRepairDTO.getRepairCost(),
                        viewRepairDTO.getTotalPrice()
                ));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colRepairId.setCellValueFactory(new PropertyValueFactory<>("repairId"));
        colVehicleNo.setCellValueFactory(new PropertyValueFactory<>("vehicleNo"));
        colRepairDate.setCellValueFactory(new PropertyValueFactory<>("repairDate"));
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colItemQty.setCellValueFactory(new PropertyValueFactory<>("itemQty"));
        colItemUnitPrice.setCellValueFactory(new PropertyValueFactory<>("itemUnitPrice"));
        colRepairCost.setCellValueFactory(new PropertyValueFactory<>("repairCost"));
        colTotalAmount.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        tblRepairDetailsTable.getItems().clear();
        String repairId = txtRepairId.getText();
        try {
            List<ViewRepairDTO> viewRepairByRepairId = orderBO.getViewRepairByRepairId(repairId);
            for (ViewRepairDTO viewRepairDTO : viewRepairByRepairId) {
                tblRepairDetailsTable.getItems().add(new ViewRepairTm(
                        viewRepairDTO.getRepairId(),
                        viewRepairDTO.getVehicleNo(),
                        viewRepairDTO.getRepairDate(),
                        viewRepairDTO.getItemCode(),
                        viewRepairDTO.getItemQty(),
                        viewRepairDTO.getItemUnitPrice(),
                        viewRepairDTO.getRepairCost(),
                        viewRepairDTO.getTotalPrice()
                ));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
