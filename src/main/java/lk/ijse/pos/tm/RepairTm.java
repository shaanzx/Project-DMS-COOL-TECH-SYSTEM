package lk.ijse.pos.tm;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RepairTm {
    private String vehicleNo;
    private String description;
    private Date repairDate;
    private double repairCost;
    private String itemCode;
    private int qty;
    private double unitPrice;
    private double totalAmount;
    private JFXButton deleteButton;
}
