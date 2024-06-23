package lk.ijse.pos.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Item {
    private String code;
    private String description;
    private String model;
    private int qtyOnHand;
    private double unitPrice;
    private String date;

}
