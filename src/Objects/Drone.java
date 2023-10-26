package Objects;

import java.util.UUID;

import Common.Position;

public class Drone {
    private UUID id;
    private String status = "Available";
    private double maxLoad;
    private double speed = 10.0;
    private double batteryStatus = 1.0;
    private Order currentOrder = null;
    private Position currentPosition;
    private String accident = "";

    public Drone(Position pos, double maxLoad){
        this.id = UUID.randomUUID();
        this.currentPosition = pos;
        this.maxLoad = maxLoad;
    }

    public double getSpeed(){
        return this.speed;
    }

    public String getStatus(){
        return this.status;
    }

    public double getBattery(){
        return this.batteryStatus;
    }

    public void changePosition(Position pos){
        this.currentPosition = pos;
    }

    public void changePosition(double deltaX, double deltaY, double deltaZ){
        this.currentPosition.movePosition(deltaX, deltaY, deltaZ);
    }

    public Position getPosition(){
        return this.currentPosition;
    }

    public UUID getID(){
        return this.id;
    }

    public int useBattery(double usage){
        if (usage >= this.batteryStatus) {
            this.status = "Empty";
            return 0; //Not successful
        } else {
            this.batteryStatus -= usage;
            return 0;
        }
    }

    public void chargeBattery(){
        this.batteryStatus = 1.0;
    }

    public int assingOrder(Order order){
        if (order.getWeight() > this.maxLoad || this.status != "Available" ){
            System.out.println("Can't assign order to this drone");
            return 0; // This means operation was not done successfully
        } else {
            this.currentOrder = order;
            this.status = "Busy";
            return 1; //This means success
        }
    }

    public void deactivateDrone(){
        this.deactivateDrone("");
    }

    public void deactivateDrone(String reason){
        if (this.status == "Busy"){
            System.out.println("Trying to cancel an active drone");
        }
        this.status = "Deactivated";
        this.accident = reason;
        this.currentOrder = null;
        this.currentPosition = null;
    }

    public void orderCompleted(){
        if (this.status == "Deactivated") {
            return;
        }
        if (this.batteryStatus <= 0){
            this.status = "Empty";
        } else {
            this.status = "Available";
        }
        this.currentOrder = null;
    }

}
