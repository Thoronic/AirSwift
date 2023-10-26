package Database;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import Common.Status;
import Objects.Customer;
import Objects.Drone;
import Objects.Order;

public class DBControl {
    private static HashMap<UUID, Customer> customerDB = new HashMap<UUID, Customer>();
    private static HashMap<UUID, Order> OrderDB = new HashMap<UUID, Order>();
    private static HashMap<UUID, Drone> DroneDB = new HashMap<UUID, Drone>(); 

    private DBControl(){
    }

    public static boolean registerObjectToDB(Object object){
        if (object.getClass() == Drone.class) {
            if (DroneDB.containsKey(((Drone) object).getID())) {
                return true;
            } else {
                DroneDB.put(((Drone) object).getID(), (Drone) object);
                return true;
            }
        } else if (object.getClass() == Order.class){
            if (OrderDB.containsKey(((Order) object).getID())) {
                return true;
            } else {
                OrderDB.put(((Order) object).getID(), (Order) object);
                return true;
            }
        } else if (object.getClass() == Customer.class){
            if (customerDB.containsKey(((Customer) object).getID())) {
                return true;
            } else {
                customerDB.put(((Customer) object).getID(), (Customer) object);
                return true;
            }
        } else {
            System.out.println("not recognized type trying to register to db");
            return false;
        }
    }

    public static Drone getFreeDrone(double weight){
        for (Map.Entry<UUID,Drone> entry : DroneDB.entrySet()) {
            if (entry.getValue().getStatus() == Status.AVAILABLE || entry.getValue().getMaxLoad() >= weight) {
                return entry.getValue();
            }
        }
        return null;
    }
    

}
