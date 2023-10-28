package com.AirSwift.AirSwift.Logic;

import com.AirSwift.AirSwift.Common.Position;
import com.AirSwift.AirSwift.Objects.CustomerDB;
import com.AirSwift.AirSwift.Objects.DroneDB;
import com.AirSwift.AirSwift.Objects.OrderDB;

public class Logic {
    public static Position posHeadquarter = new Position();
    private Logic(){}

    public static String registerCustomer(String Name, String handle, Position pos){
        if (!CustomerDB.isHandleAvailable(handle) || handle.length() > 0){
            return "Can't register customer, handle is already in use or handle is wrong.";
        } else {
            CustomerDB.addCustomer(Name, handle, pos);
            return "Customer successfully registered.";
        }
    }

    public static String registerDrone(double maxLoad, double speed){
        if (maxLoad <= 0 || speed <= 0) {
            return "Can't register drone, invalid values given.";
        } else {
            DroneDB.addDrone(posHeadquarter, maxLoad, speed);
            return "Drone successfully registered.";
        }
    }

    public static String newOrder(String customerHandle, Position startPos, Double weight){
        if (CustomerDB.isHandleAvailable(customerHandle)) {
            return "Customer not registered or handle wrong, please register first before ordering.";
        }
        int droneID = DroneDB.getFreeDrone(weight, startPos);
        if (droneID == -1 ){
            return "Currently there are no drones available to carry such a weight. Please try again later.";
        }
        int orderID = OrderDB.addOrder(startPos, CustomerDB.getCustomerIDfromHandle(customerHandle), weight);
        if (orderID == -1) {
            return "Error: Couldn't add a new order!";
        }
        DroneDB.assignDronetoOrder(droneID, orderID);
        return "Order " + orderID + " successfully assigned to Drone " + droneID + ", order is underway.";
    }

    
    
}
