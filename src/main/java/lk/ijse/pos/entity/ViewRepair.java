package lk.ijse.pos.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ViewRepair {
    private String repairId;
    private String vehicleNo;
    private Date repairDate;
    private String itemCode;
    private int itemQty;
    private double itemUnitPrice;
    private double repairCost;
    private double totalPrice;
}
