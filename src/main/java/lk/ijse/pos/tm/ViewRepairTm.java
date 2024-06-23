package lk.ijse.pos.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ViewRepairTm {
    private String repairId;
    private String vehicleNo;
    private Date repairDate;
    private String itemCode;
    private int itemQty;
    private double itemUnitPrice;
    private double repairCost;
    private double totalPrice;
}
