package lk.ijse.pos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ViewOrdersDTO {
    private String customerId;
    private String orderId;
    private Date date;
    private String itemCode;
    private int itemQty;
    private double itemUnitPrice;
    private double totalAmount;
}
