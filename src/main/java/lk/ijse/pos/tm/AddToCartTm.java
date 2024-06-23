package lk.ijse.pos.tm;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddToCartTm {
    private String itemCode;
    private String itemName;
    private double unitPrice;
    private int qty;
    private double totalAmount;
    private JFXButton btnDelete;
}
