package lk.ijse.pos.bo;

import lk.ijse.pos.bo.custom.impl.*;

public class BOFactory {
    public static BOFactory boFactory;

    public enum BOType{
        CUSTOMER,EMPLOYEE,DASHBOARD,ITEM,PLACE_ORDER,PAYMENT,REGISTER,REPAIR,USER,VEHICLE,VIEW_ORDER,VIEW_REPAIR
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
            case PLACE_ORDER:
                return new PlaceOrderBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            case VEHICLE:
                return new VehicleBOImpl();
            case VIEW_ORDER:
                return new OrderBOImpl();
            case VIEW_REPAIR:
                return new OrderBOImpl();
            case USER:
                return new UserBOImpl();
            default:
                return null;
        }
    }
}
