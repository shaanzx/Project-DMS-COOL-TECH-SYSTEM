package lk.ijse.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class ItemFormController {

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
    private TableView<?> tblItem;

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

    @FXML
    void btnItemDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnItemSaveOnAction(ActionEvent event) {

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
