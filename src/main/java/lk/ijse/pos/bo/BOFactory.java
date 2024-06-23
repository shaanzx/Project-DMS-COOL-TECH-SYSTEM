package lk.ijse.pos.bo;

import lk.ijse.pos.bo.custom.CustomerBO;
import lk.ijse.pos.bo.custom.impl.*;

public class BOFactory {
    public static BOFactory boFactory;

    public enum BOType{
        CUSTOMER,DASHBOARD,ITEM,PLACE_ORDER,REGISTER,REPAIR,USER,VEHICLE
    }
    public BOFactory() {}

    public static BOFactory getBoFactory() {
        return (boFactory == null)? boFactory = new BOFactory() : boFactory;
    }


    public SuperBO getBOType(BOType type){
        switch (type){
            case CUSTOMER:
                return new CustomerBOImpl();
            /*case DASHBOARD:
                return new DashBoardBOImpl();
            case ITEM:
                return new ItemBOImpl();
            case PLACE_ORDER:
                return new PlaceOrderBOImpl();
            case REGISTER:
                return new RegisterBOImpl();
            case REPAIR:
                return new RepairBOImpl();*/
            case USER:
                return new UserBOImpl();
          /*  case VEHICLE:
                return new VehicleBOImpl();*/
            default:
                return null;
        }
    }
}
