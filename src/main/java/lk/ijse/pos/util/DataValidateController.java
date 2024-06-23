package lk.ijse.pos.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidateController {
    //Customer class
    public  static  boolean validateCustomerName(String name){
        String nameRegex = "^[A-z|\\s]{3,}$";
        Pattern pattern = Pattern.compile(nameRegex);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }
    public static boolean validateCustomerAddress(String address){
        String addressRegex = "^([A-z0-9]|[-/,.@+]|\\s){4,}$";
        Pattern pattern = Pattern.compile(addressRegex);
        Matcher matcher = pattern.matcher(address);
        return matcher.matches();
    }
    public static boolean validateCustomerTel(String tel){
        String telRegex = "^([+]94{1,3}|[0])([1-9]{2})([0-9]){7}$";
        Pattern pattern = Pattern.compile(telRegex);
        Matcher matcher = pattern.matcher(tel);
        return matcher.matches();
    }
    public static boolean validateCustomerEmail(String email){
        String emailRegex = "^([A-z])([A-z0-9.]){1,}[@]([A-z0-9]){1,10}[.]([A-z]){2,5}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    //Employee class
    public static boolean validateEmployeeName(String name){
        String nameRegex = "^[A-z|\\s]{3,}$";
        Pattern pattern = Pattern.compile(nameRegex);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }
    public static boolean validateEmployeeAddress(String address){
        String addressRegex = "^([A-z0-9]|[-/,.@+]|\\s){4,}$";
        Pattern pattern = Pattern.compile(addressRegex);
        Matcher matcher = pattern.matcher(address);
        return matcher.matches();
    }
    public static boolean validateEmployeeTel(String tel){
        String telRegex = "^([+]94{1,3}|[0])([1-9]{2})([0-9]){7}$";
        Pattern pattern = Pattern.compile(telRegex);
        Matcher matcher = pattern.matcher(tel);
        return matcher.matches();
    }
    public static boolean validateEmployeeJobRole(String job){
        String jobroleRegex = "^[A-z|\\s]{3,}$";
        Pattern pattern = Pattern.compile(jobroleRegex);
        Matcher matcher = pattern.matcher(job);
        return matcher.matches();
    }

    //Item class
    public static boolean validateItemName(String name){
        String nameRegex = "^[A-z|\\s]{3,}$";
        Pattern pattern = Pattern.compile(nameRegex);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }
    public static boolean validateVehicleModel(String price){
        String vehicleModelRegex = "^[A-z|\\s]{3,}$";
        Pattern pattern = Pattern.compile(vehicleModelRegex);
        Matcher matcher = pattern.matcher(price);
        return matcher.matches();
    }
    public static boolean validateItemQty(String qty){
        String qtyRegex = "^[0-9]{1,}$";
        Pattern pattern = Pattern.compile(qtyRegex);
        Matcher matcher = pattern.matcher(qty);
        return matcher.matches();
    }
    public static boolean validateItemPrice(String price){
        String priceRegex = "^([0-9]){1,}[.]([0-9]){1,}$";
        Pattern pattern = Pattern.compile(priceRegex);
        Matcher matcher = pattern.matcher(price);
        return matcher.matches();
    }

    //Vehicle class
    public static boolean validateVehicleNo(String no){
        String vehicleNoRegex = "^[A-Z]{2,3}[-]\\d{4}$";
        Pattern pattern = Pattern.compile(vehicleNoRegex);
        Matcher matcher = pattern.matcher(no);
        return matcher.matches();
    }
    public static boolean validateVehicleModels(String model){
        String vehicleModelRegex = "^[A-z|\\s]{3,}$";
        Pattern pattern = Pattern.compile(vehicleModelRegex);
        Matcher matcher = pattern.matcher(model);
        return matcher.matches();
    }
    public  static boolean validateVehicleType(String type){
        String vehicleTypeRegex = "^[A-z|\\s]{3,}$";
        Pattern pattern = Pattern.compile(vehicleTypeRegex);
        Matcher matcher = pattern.matcher(type);
        return matcher.matches();
    }

    //User Class
    public static boolean validateUserName(String name){
        String nameRegex = "^[a-z|\\s]{3,}$";
        Pattern pattern = Pattern.compile(nameRegex);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }
    public static boolean validateUserPassword(String password){
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
