package lk.ijse.pos.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDetails {
    private String orderId;
    private String itemCode;
    private Date orderDate;
    private int qty;
    private  double unitPrice;
    private double OrderAmount;
}
