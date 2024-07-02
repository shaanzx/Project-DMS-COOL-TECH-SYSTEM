package lk.ijse.pos.bo;

import lk.ijse.pos.bo.custom.CustomerBO;
import lk.ijse.pos.bo.custom.impl.*;

public class BOFactory {
    public static BOFactory boFactory;

    public enum BOType{
        CUSTOMER,EMPLOYEE,DASHBOARD,ITEM,PLACE_ORDER,REGISTER,REPAIR,USER,VEHICLE
    }
    public BOFactory() {}

    public static BOFactory getBoFactory() {
        return (boFactory == null)? boFactory = new BOFactory() : boFactory;
    }

    public SuperBO getBOType(BOType type){
        switch (type){
            case CUSTOMER:
                return new CustomerBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case ITEM:
                return new ItemBOImpl();
            case VEHICLE:
                new VehicleBOImpl();
            case USER:
                return new UserBOImpl();
            default:
                return null;
        }
    }
}
