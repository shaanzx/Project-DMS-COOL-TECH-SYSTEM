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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.CustomerBO;
import lk.ijse.pos.bo.custom.ItemBO;
import lk.ijse.pos.bo.custom.PaymentBO;
import lk.ijse.pos.bo.custom.PlaceOrderBO;
import lk.ijse.pos.dao.custom.OrderDetailsDAO;
import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.dto.*;
import lk.ijse.pos.tm.AddToCartTm;
import lk.ijse.pos.util.Mail;
import lk.ijse.pos.util.Navigation;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class PlaceOrderFormController implements Initializable {

    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBOType(BOFactory.BOType.CUSTOMER);
    ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBOType(BOFactory.BOType.ITEM);
    PlaceOrderBO placeOrderBO = (PlaceOrderBO) BOFactory.getBoFactory().getBOType(BOFactory.BOType.PLACE_ORDER);
    PaymentBO paymentBO = (PaymentBO) BOFactory.getBoFactory().getBOType(BOFactory.BOType.PAYMENT);

    @FXML
    private JFXButton btnOrderConfirm;

    @FXML
    private JFXComboBox<String> cmbCustomerId;

    @FXML
    private JFXComboBox<String> cmbItemCode;

    @FXML
    private TableColumn<?, ?> colDeleteItem;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colItemName;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colTotalAmount;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private DatePicker dpOrderDate;

    @FXML
    private Label lblBalance;

    @FXML
    private Label lblCustomerName;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblItemName;

    @FXML
    private Label lblMoreMoney;

    @FXML
    private Label lblNeeded;

    @FXML
    private Label lblNetAmount;

    @FXML
    private Label lblOrderId;

    @FXML
    private Label lblQtyOnHand;

    @FXML
    private Label lblUnitPrice;

    @FXML
    private Pane pagingPane;

    @FXML
    private TableView<AddToCartTm> tblOrderDetail;

    @FXML
    private TextField txtCash;

    @FXML
    private TextField txtCustomerMobile;

    @FXML
    private TextField txtQty;

    private ObservableList<AddToCartTm> cartList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblOrderId.setText(generateNextOrderId());
        lblOrderId.setDisable(true);
        setCellValueFactory();
        setDate();
        getCustomerId();
        getItemCode();
        lblNeeded.setVisible(false);
        btnOrderConfirm.setDisable(true);
    }

    private void getItemCode() {
        ObservableList<String> itemCodes = FXCollections.observableArrayList();
        try {
            List<String> allItemCode = itemBO.getItemCodes();
            for(String itemCode : allItemCode) {
                itemCodes.add(itemCode);
            }
            cmbItemCode.setItems(itemCodes);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void getCustomerId() {
        ObservableList<String> customerIds = FXCollections.observableArrayList();
        try {
            List<String> customerId = customerBO.getCustomerId();
            for(String ids : customerId) {
                customerIds.add(ids);
            }
            cmbCustomerId.setItems(customerIds);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateNextOrderId() {
        try {
            ResultSet resultSet = placeOrderBO.generateNextOrderId();
            String currentOrderId = null;
            if(resultSet.next()) {
                currentOrderId = resultSet.getString(1);
                return nextOrderId(currentOrderId);
            }
            return nextOrderId(null);
        } catch (SQLException | ClassNotFoundException e) {
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

    private void setDate() {
        lblDate.setText(String.valueOf(LocalDate.now()));
    }

    private void setCellValueFactory() {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colTotalAmount.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
        colDeleteItem.setCellValueFactory(new PropertyValueFactory<>("btnDelete"));
    }

    private void calculateNetAmount() {
        double netAmount = 0;
        for(int i = 0; i < tblOrderDetail.getItems().size(); i++){
            netAmount += (double) colTotalAmount.getCellData(i);
        }
        lblNetAmount.setText(String.valueOf(netAmount));
    }

    private File getBill() throws JRException, SQLException, ClassNotFoundException {
        JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/reports/order.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getDbConnection().getConnection());

        // Export the report to a PDF file
        File pdfFile = new File("Order Receipt.pdf");
        JasperExportManager.exportReportToPdfFile(jasperPrint, pdfFile.getAbsolutePath());

        return pdfFile;
    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        String itemCode = cmbItemCode.getValue();
        String itemName = lblItemName.getText();
        double unitPrice = Double.parseDouble(lblUnitPrice.getText());
        int qty = Integer.parseInt(txtQty.getText());
        double totalAmount = unitPrice * qty;
        JFXButton btnDelete = new JFXButton("Delete");
        btnDelete.setCursor(Cursor.HAND);

        btnDelete.setOnAction((e)->{
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to delete?", yes, no).showAndWait();
            if(type.orElse(no) == yes){
                int index = tblOrderDetail.getSelectionModel().getSelectedIndex();
                cartList.remove(index);
                tblOrderDetail.refresh();
                calculateNetAmount();
            }
        });
        for(int i = 0; i < tblOrderDetail.getItems().size(); i++) {
            if (itemCode.equals(colItemCode.getCellData(i))) {
                qty += cartList.get(i).getQty();
                totalAmount = unitPrice * qty;
                cartList.get(i).setQty(qty);
                cartList.get(i).setTotalAmount(totalAmount);
                tblOrderDetail.refresh();
                calculateNetAmount();
                txtQty.clear();
                cmbItemCode.requestFocus();
                return;
            }
        }
        AddToCartTm act = new AddToCartTm(itemCode, itemName, unitPrice, qty, totalAmount, btnDelete);
        cartList.add(act);
        tblOrderDetail.setItems(cartList);
        txtQty.clear();
        calculateNetAmount();
    }

    @FXML
    void btnNewCustomerOnAction(ActionEvent event) {
        try {
            Navigation.switchPaging(pagingPane,"customer_form.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        String orderId = lblOrderId.getText();
        String customerId = cmbCustomerId.getValue();
        Date date = Date.valueOf(lblDate.getText());

        OrderDTO orderDTO = new OrderDTO(orderId, customerId, date);

        CustomerDTO customerDTO = null;
        try {
            customerDTO = customerBO.searchCustomer(customerId);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String cusEmail = customerDTO.getEmail();
        System.out.println(cusEmail);
        List<OrderDetailsDTO> orderList = new ArrayList<>();
        double netAmount = 0;
        double orderAmount = 0;

        for(int i=0; i < tblOrderDetail.getItems().size(); i++){
            AddToCartTm addToCartTm = cartList.get(i);

            OrderDetailsDTO orderDetails = new OrderDetailsDTO(
                    orderId,
                    addToCartTm.getItemCode(),
                    date,
                    addToCartTm.getQty(),
                    addToCartTm.getUnitPrice(),
                    orderAmount += addToCartTm.getQty() * addToCartTm.getUnitPrice()
            ) {
            };
            orderList.add(orderDetails);

            netAmount += addToCartTm.getTotalAmount();
        }
        double printcash = Double.parseDouble(txtCash.getText());
        double balance = Double.parseDouble(lblBalance.getText());

        String paymentId = null;
        try {
            paymentId = paymentBO.generatePaymentId();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        PaymentDTO payment = new PaymentDTO(paymentId, customerId, orderId,null, netAmount, date,printcash,balance);

        OrderPlaceDTO orderPlace = new OrderPlaceDTO(orderDTO, orderList , payment);
        try {
            boolean isOrderPlaced = false;
            try {
                isOrderPlaced = placeOrderBO.orderPlace(orderPlace);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            if (isOrderPlaced) {
                ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
                Optional<ButtonType> result = new Alert(Alert.AlertType.CONFIRMATION, "Order Completed.Do you want to generate a bill?", yes, no).showAndWait();

                if (result.orElse(no) == yes) {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("param1",printcash);
                    parameters.put("param2",balance);
                    InputStream resource = this.getClass().getResourceAsStream("/reports/order.jrxml");
                    try {
                        Mail.setMail("Order Completed", "Order Completed", "Thank you for your order. Your order is successfully placed. Your order id is "+orderId+".", cusEmail, getBill());
                    } catch (MessagingException | IOException | JRException e) {
                        throw new RuntimeException(e);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    try{
                        JasperReport jasperReport = JasperCompileManager.compileReport(resource);
                        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters,DBConnection.getDbConnection().getConnection());;
                        JasperViewer.viewReport(jasperPrint, false);
                    }catch (JRException | ClassNotFoundException e){
                        e.printStackTrace();
                    }
                }
            } else {
                new Alert(Alert.AlertType.WARNING, "Something went wrong. Please try again").show();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnViewOrderDetailsOnAction(ActionEvent event) {
        Navigation.changeStage("/view/viewOrders_Form.fxml","Order Details Form");
    }

    @FXML
    void cmbCustomerIdOnAction(ActionEvent event) {
        String customerId = cmbCustomerId.getValue();

        try {
            CustomerDTO customerDTO = customerBO.searchCustomer(customerId);
            lblCustomerName.setText(customerDTO.getName());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void cmbItemCodeOnAction(ActionEvent event) {
        String itemCode = cmbItemCode.getValue();

        try {
            ItemDTO itemDTO = itemBO.searchItem(itemCode);
            if(itemDTO!=null) {
                lblItemName.setText(itemDTO.getDescription());
                lblUnitPrice.setText(String.valueOf(itemDTO.getUnitPrice()));
                lblQtyOnHand.setText(String.valueOf(itemDTO.getQtyOnHand()));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void keyCash(KeyEvent event) {
        if (!txtCash.getText().isEmpty()) {
            double balance = Double.parseDouble(txtCash.getText()) - Double.parseDouble(lblNetAmount.getText());
            if (balance >= 0) {
                txtCash.setStyle("-fx-text-fill: black");
                lblBalance.setText(String.valueOf(balance));
                lblNeeded.setVisible(false);
                lblMoreMoney.setText("");
                btnOrderConfirm.setDisable(false);
                lblBalance.setVisible(true);
            } else if (balance < 0) {
                txtCash.setStyle("-fx-text-fill: black");
                btnOrderConfirm.setDisable(true);
                double positbalance = Math.abs(balance);
                lblNeeded.setVisible(true);
                lblMoreMoney.setText(positbalance + "/=");
                lblBalance.setVisible(false);
            }
        } else {
            btnOrderConfirm.setDisable(true);
            txtCash.setStyle("-fx-text-fill: red");
            lblBalance.setVisible(false);
            lblNeeded.setVisible(false);
            lblMoreMoney.setText("");
        }
    }

    @FXML
    void lblBalanceOnAction(MouseEvent event) {

    }

    @FXML
    void lblNetAmountOnAction(MouseEvent event) {

    }

    @FXML
    void txtQtyOnAction(ActionEvent event) {
        btnAddToCartOnAction(event);
    }

    @FXML
    void txtSearchCustomerTelephoneOnAction(ActionEvent event) {
        String tel = txtCustomerMobile.getText();
        try {
            CustomerDTO customerDTO = customerBO.searchByMobile(tel);
            if(customerDTO != null){
                lblCustomerName.setText(customerDTO.getName());
                cmbCustomerId.setValue(customerDTO.getId());
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
