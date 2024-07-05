package lk.ijse.pos.dao;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.bo.custom.impl.RegisterBOImpl;
import lk.ijse.pos.bo.custom.impl.RepairBOImpl;
import lk.ijse.pos.bo.custom.impl.UserBOImpl;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.dao.custom.UserDAO;
import lk.ijse.pos.dao.custom.impl.*;

public class DAOFactory {
    public  static DAOFactory daoFactory;

    public enum DAOType{
        CUSTOMER,EMPLOYEE,DASHBOARD,ITEM,ORDER,ORDER_DETAILS,PAYMENT,REGISTER,REPAIR,USER,VEHICLE,VIEW_ORDER
    }

    public DAOFactory() {}

    public static DAOFactory getDaoFactory(){
        return (daoFactory==null)?daoFactory=new DAOFactory():daoFactory;
    }

    public SuperDAO getDaoType(DAOType daoType){
        switch(daoType){
           case CUSTOMER:
                return new CustomerDAOImpl();
           case EMPLOYEE:
               return new EmployeeDAOImpl();
           case ITEM:
               return new ItemDAOImpl();
           case ORDER:
               return new OrderDAOImpl();
           case ORDER_DETAILS:
               return new OrderDetailsDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
           case VEHICLE:
               return new VehicleDAOImpl();
           case USER:
               return new UserDAOImpl();
            case VIEW_ORDER:
                return new QueryDAOImpl();
           default:
               return null;
        }
    }
}
