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
    private OrderDTO order;
    private List<OrderDetailsDTO> orderDetails;
    private RepairDTO repair;
    private PaymentDTO payment;
}
