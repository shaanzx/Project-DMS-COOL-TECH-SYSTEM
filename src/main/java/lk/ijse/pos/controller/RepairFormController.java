package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.*;
import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.dto.*;
import lk.ijse.pos.entity.OrderDetails;
import lk.ijse.pos.tm.AddToCartTm;
import lk.ijse.pos.tm.RepairTm;
import lk.ijse.pos.util.Navigation;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class RepairFormController implements Initializable {
    RepairBO repairBO = (RepairBO) BOFactory.getBoFactory().getBOType(BOFactory.BOType.REPAIR);
    PlaceOrderBO placeOrderBO = (PlaceOrderBO) BOFactory.getBoFactory().getBOType(BOFactory.BOType.PLACE_ORDER);
    VehicleBO vehicleBO = (VehicleBO) BOFactory.getBoFactory().getBOType(BOFactory.BOType.VEHICLE);
    ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBOType(BOFactory.BOType.ITEM);
    EmployeeBO employeeBO = (EmployeeBO) BOFactory.getBoFactory().getBOType(BOFactory.BOType.EMPLOYEE);
    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBOType(BOFactory.BOType.CUSTOMER);
    PaymentBO paymentBO = (PaymentBO) BOFactory.getBoFactory().getBOType(BOFactory.BOType.PAYMENT);
    @FXML
    private JFXButton btnOrderPlace;

    @FXML
    private JFXComboBox<String> cmbEmployeeId;

    @FXML
    private JFXComboBox<String> cmbItemCode;

    @FXML
    private JFXComboBox<String> cmbVehicleNo;

    @FXML
    private TableColumn<?, ?> colIetmQty;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colItemUnitPrice;

    @FXML
    private TableColumn<?, ?> colRemove;

    @FXML
    private TableColumn<?, ?> colRepairCost;

    @FXML
    private TableColumn<?, ?> colRepairDate;

    @FXML
    private TableColumn<?, ?> colRepairDescription;

    @FXML
    private TableColumn<?, ?> colTotalPrice;

    @FXML
    private TableColumn<?, ?> colVehicleNo;

    @FXML
    private DatePicker dpRepairDate;

    @FXML
    private Label lblBalance;

    @FXML
    private Label lblCustomerName;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblEmployeeName;

    @FXML
    private Label lblItemName;

    @FXML
    private Label lblItemQtyOnHand;

    @FXML
    private Label lblMoreMoney;

    @FXML
    private Label lblNeeded;

    @FXML
    private Label lblNetAmount;

    @FXML
    private Label lblOrderId;

    @FXML
    private Label lblRepairId;

    @FXML
    private Label lblUnitPrice;

    @FXML
    private Pane pagingPane;

    @FXML
    private TableView<RepairTm> tblRepairDetails;

    @FXML
    private TextField txtPayment;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtRepairCost;

    @FXML
    private TextField txtRepairDescription;

    private ObservableList<RepairTm> addToCartRepairList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblRepairId.setText(generateNextRepairId());
        lblOrderId.setText(generateNextOrderId());
        getVehicleNo();
        getEmployeeId();
        getItemCode();
        setDate();
        setCellValueFactory();
        lblNeeded.setVisible(false);
        btnOrderPlace.setDisable(true);
    }

    private void setCellValueFactory() {
        colVehicleNo.setCellValueFactory(new PropertyValueFactory<>("vehicleNo"));
        colRepairDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colRepairDate.setCellValueFactory(new PropertyValueFactory<>("repairDate"));
        colRepairCost.setCellValueFactory(new PropertyValueFactory<>("repairCost"));
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colIetmQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colItemUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotalPrice.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
        colRemove.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));
    }

    private void setDate() {
        lblDate.setText(String.valueOf(LocalDate.now()));
    }

    private void getItemCode() {
        ObservableList<String> itemCodeList = FXCollections.observableArrayList();

        try {
            ArrayList<ItemDTO> allItems = itemBO.getAllItems();
            for(ItemDTO itemDTO : allItems){
                itemCodeList.add(itemDTO.getCode());
            }
            cmbItemCode.setItems(itemCodeList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void getEmployeeId() {
        ObservableList<String> employeeIds = FXCollections.observableArrayList();
        try {
            ArrayList<EmployeeDTO> allEmployee = employeeBO.getAllEmployee();
            for(EmployeeDTO employeeDTO : allEmployee){
                employeeIds.add(employeeDTO.getId());
            }
            cmbEmployeeId.setItems(employeeIds);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void getVehicleNo() {
        ObservableList<String> vehicleList = FXCollections.observableArrayList();

        try {
            ArrayList<VehicleDTO> allVehicle = vehicleBO.getAllVehicle();
            for(VehicleDTO vehicleDTO : allVehicle ){
               vehicleList.add(vehicleDTO.getVehicleNo());
            }
            cmbVehicleNo.setItems(vehicleList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateNextOrderId() {
        try {
            ResultSet resultSet = placeOrderBO.generateNextOrderId();
            String currentOrderId = null;
            if(resultSet.next()){
                currentOrderId = resultSet.getString(1);
                return nextOrderId(currentOrderId);
            }
            return nextOrderId(currentOrderId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String nextOrderId(String currentOrderId) {
        String nextOrderId=null;
        if (currentOrderId==null){
            nextOrderId="OR001";
        }else {
            String data = currentOrderId.replace("OR", "");
            int num = Integer.parseInt(data);
            num++;

            if (num >= 1 && num <= 9) {
                nextOrderId = "OR00" + num;
            } else if (num >= 10 && num <= 99) {
                nextOrderId = "OR0" + num;
            } else if (num >= 100 && num <= 999) {
                nextOrderId = "OR" + num;
            }
        }
        return nextOrderId;
    }

    private String generateNextRepairId() {
        try {
            ResultSet resultSet = repairBO.generateRepairId();
            String currentRepairId = null;
            if(resultSet.next()){
                currentRepairId = resultSet.getString(1);
                return nextRepairId(currentRepairId);
            }
            return nextRepairId(null);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String nextRepairId(String currentRepairId) {
        String nextRepairId=null;
        if (currentRepairId==null){
            nextRepairId="R001";
        }else {
            String data = currentRepairId.replace("R", "");
            int num = Integer.parseInt(data);
            num++;

            if (num >= 1 && num <= 9) {
                nextRepairId = "R00" + num;
            } else if (num >= 10 && num <= 99) {
                nextRepairId = "R0" + num;
            } else if (num >= 100 && num <= 999) {
                nextRepairId = "R" + num;
            }
        }
        return nextRepairId;
    }

    private void calculateNetAmount() {
        double netAmount = 0;
        for(int i = 0; i < tblRepairDetails.getItems().size(); i++){
            netAmount += (double) colTotalPrice.getCellData(i);
        }
        lblNetAmount.setText(String.valueOf(netAmount));
    }

    @FXML
    void btnAddNewVehicleOnAction(ActionEvent event) {
        try {
            Navigation.switchPaging(pagingPane,"vehicle_form.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {

        String vehicleNo = cmbVehicleNo.getValue();
        String description = txtRepairDescription.getText();
        Date repairDate = Date.valueOf(dpRepairDate.getValue());
/*        double repairCost = 0;
        for(int i = 0; i < 10; i++){
            if(i==0){
                repairCost = Double.parseDouble(txtRepairCost.getText());
            }else{
                repairCost = 0;
            }
        }
        double repairCost = 0;
        for(int i = 0; i < tblRepairDetails.getItems().size(); i++) {
            if(i == 0) {
                repairCost = Double.parseDouble(txtRepairCost.getText());
                System.out.println(repairCost);
            } else if (i > 0) {
                repairCost = 0;
            }
        }*/
        double repairCost = Double.parseDouble(txtRepairCost.getText());
        String itemCode = cmbItemCode.getValue();
        int qty = Integer.parseInt(txtQty.getText());
        double unitPrice = Double.parseDouble(lblUnitPrice.getText());
        double totalAmount = repairCost+(qty*unitPrice);
        JFXButton btnDelete = new JFXButton("Remove");
        btnDelete.setCursor(Cursor.HAND);

        btnDelete.setOnAction((e)->{
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to delete?", yes, no).showAndWait();
            if(type.orElse(no) == yes){
                int index = tblRepairDetails.getSelectionModel().getSelectedIndex();
                addToCartRepairList.remove(index);
                tblRepairDetails.refresh();
                calculateNetAmount();
            }
        });
        for (int i = 0; i < tblRepairDetails.getItems().size(); i++) {
            /*if(i == 0){
                totalAmount = repairCost+(qty*unitPrice);
            } else if (i > 0) {
                totalAmount = qty*unitPrice;
            }*/
            if (itemCode.equals(colItemCode.getCellData(i))) {
                qty += addToCartRepairList.get(i).getQty();
                totalAmount = unitPrice * qty;
                addToCartRepairList.get(i).setQty(qty);
                addToCartRepairList.get(i).setTotalAmount(totalAmount);
                tblRepairDetails.refresh();
                calculateNetAmount();
                txtQty.clear();
                cmbItemCode.requestFocus();
                return;
            }
        }
        RepairTm repair = new RepairTm(vehicleNo, description, repairDate,   repairCost, itemCode, qty, unitPrice, totalAmount, btnDelete);
        addToCartRepairList.add(repair);
        tblRepairDetails.setItems(addToCartRepairList);
        txtQty.clear();
        calculateNetAmount();
    }

    @FXML
    void btnConfirmRepairBillOnAction(ActionEvent event) {
        String orderId = lblOrderId.getText();
        VehicleDTO vehicleDTO = null;
        try {
            vehicleDTO = vehicleBO.searchVehicle(cmbVehicleNo.getValue());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Date date = Date.valueOf(lblDate.getText());

        OrderDTO order = new OrderDTO(orderId,vehicleDTO.getCustomerId(), date);

        List<OrderDetailsDTO> orderList = new ArrayList<>();
        double netAmount = 0;
        double orderAmount = 0;

        for(int i=0; i < tblRepairDetails.getItems().size(); i++){
            RepairTm repairTm = addToCartRepairList.get(i);

            OrderDetailsDTO orderDetails = new OrderDetailsDTO(
                    orderId,
                    repairTm.getItemCode(),
                    date,
                    repairTm.getQty(),
                    repairTm.getUnitPrice(),
                    orderAmount += repairTm.getQty() * repairTm.getUnitPrice()
            );
            orderList.add(orderDetails);
            netAmount += repairTm.getTotalAmount();
        }


        RepairDTO repair = new RepairDTO(
                lblRepairId.getText(),
                vehicleDTO.getVehicleNo(),
                txtRepairDescription.getText(),
                Date.valueOf(dpRepairDate.getValue()),
                Double.valueOf(txtRepairCost.getText()),
                cmbEmployeeId.getValue(),
                cmbItemCode.getValue(),
                netAmount
        );

        double customerPayment= Double.parseDouble(txtPayment.getText());
        double balance =  Double.parseDouble(lblBalance.getText());

        PaymentDTO  payment = null;
        try {
            payment = new PaymentDTO(
                    paymentBO.generatePaymentId(),
                    vehicleDTO.getCustomerId(),
                    orderId,
                    lblRepairId.getText(),
                    netAmount,
                    Date.valueOf(lblDate.getText()),
                    customerPayment,
                    balance
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        RepairDetailsDTO repairDetails = new RepairDetailsDTO(order, orderList, repair,payment);

        try {
            boolean isRepairDone = repairBO.addNewRepair(repairDetails);
            if (isRepairDone) {
                //new Alert(Alert.AlertType.CONFIRMATION, "Repair Process Done").show();
                ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
                Optional<ButtonType> type = new Alert(Alert.AlertType.CONFIRMATION, "Repair Process Done.Do You want To Bill?", yes, no).showAndWait();
                if(type.orElse(no) == yes) {
                    try {
                        JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/reports/Payment.jrxml");
                        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

                        Map<String, Object> data = new HashMap<>();
                        data.put("RepairId", lblRepairId.getText());

                        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, data, DBConnection.getDbConnection().getConnection());
                        JasperViewer.viewReport(jasperPrint, false);

                    } catch (JRException | SQLException e) {
                        new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                        e.printStackTrace();
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Error generating receipt");
                        alert.setContentText("An error occurred while generating the receipt. Check the logs for more details.");
                        alert.showAndWait();
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnViewRepairDetailsOnAction(ActionEvent event) {
        Navigation.changeStage("/view/viewRepair_Form.fxml","View Repair Details Form");
    }

    @FXML
    void cmbEmployeeIdOnAction(ActionEvent event) {
        String employeeId = cmbEmployeeId.getValue();
        try {
            EmployeeDTO employeeDTO = employeeBO.searchEmployee(employeeId);
            lblEmployeeName.setText(employeeDTO.getName());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void cmbItemCodeOnAction(ActionEvent event) {
        String itemCode = cmbItemCode.getValue();
        try {
            ItemDTO itemDTO = itemBO.searchItem(itemCode);
            lblItemName.setText(itemDTO.getDescription());
            lblItemQtyOnHand.setText(String.valueOf(itemDTO.getQtyOnHand()));
            lblUnitPrice.setText(String.valueOf(itemDTO.getUnitPrice()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void cmbVehicleNoOnAction(ActionEvent event) {
        String vehicleNo = cmbVehicleNo.getValue();
        try {
            VehicleDTO vehicleDTO = vehicleBO.searchVehicle(vehicleNo);
            CustomerDTO customerDTO = customerBO.searchCustomer(vehicleDTO.getCustomerId());
            lblCustomerName.setText(customerDTO.getName());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void keyCash(KeyEvent event) {
        if (!txtPayment.getText().isEmpty()) {
            double balance = Double.parseDouble(txtPayment.getText()) - Double.parseDouble(lblNetAmount.getText());
            if (balance >= 0) {
                txtPayment.setStyle("-fx-text-fill: black");
                lblBalance.setText(String.valueOf(balance));
                lblNeeded.setVisible(false);
                lblMoreMoney.setText("");
                btnOrderPlace.setDisable(false);
                lblBalance.setVisible(true);
            } else if (balance < 0) {
                txtPayment.setStyle("-fx-text-fill: black");
                btnOrderPlace.setDisable(true);
                double positbalance = Math.abs(balance);
                lblNeeded.setVisible(true);
                lblMoreMoney.setText(positbalance + "/=");
                lblBalance.setVisible(false);
            }
        } else {
            btnOrderPlace.setDisable(true);
            txtPayment.setStyle("-fx-text-fill: red");
            lblBalance.setVisible(false);
            lblNeeded.setVisible(false);
            lblMoreMoney.setText("");
        }
    }

    @FXML
    void txtQtyOnAction(ActionEvent event) {
        btnAddToCartOnAction(event);
    }

}
