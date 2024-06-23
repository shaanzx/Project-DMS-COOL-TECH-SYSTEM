package lk.ijse.pos.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ViewOrderTm {
    private String customerId;
    private String orderId;
    private Date date;
    private String itemCode;
    private int itemQty;
    private double itemUnitPrice;
    private double totalAmount;
}
