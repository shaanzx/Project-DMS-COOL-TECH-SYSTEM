package lk.ijse.pos.dto;

import lk.ijse.pos.entity.Order;
import lk.ijse.pos.entity.OrderDetails;
import lk.ijse.pos.entity.Payment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RepairDetailsDTO {
    private Order order;
    private List<OrderDetails> orderDetails;
    private RepairDTO repair;
    private Payment payment;
}
