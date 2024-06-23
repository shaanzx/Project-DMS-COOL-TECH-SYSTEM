package lk.ijse.pos.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RepairDetails {
    private Order order;
    private List<OrderDetails> orderDetails;
    private Repair repair;
    private Payment payment;
}
