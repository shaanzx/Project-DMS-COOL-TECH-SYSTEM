package lk.ijse.pos.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VehicleTm {
    private String vehicleNo;
    private String model;
    private String type;
    private String customerId;
}
