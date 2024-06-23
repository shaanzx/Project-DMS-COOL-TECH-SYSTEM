package lk.ijse.pos.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Payment {
    private String paymentId;
    private String customerId;
    private String orderId;
    private String repairId;
    private double totalAmount;
    private Date  paymentDate;
    private double payCash;
    private double balance;
}
