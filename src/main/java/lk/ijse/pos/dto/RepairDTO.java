package lk.ijse.pos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RepairDTO {
    private String repairId;
    private String vehicleNo;
    private String description;
    private Date repairDate;
    private double repairCost;
    private  String employeeId;
    private String itemCode;
    private double totalAmount;
}
