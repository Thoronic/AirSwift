package Objects;

import java.util.UUID;

import Common.Position;
import Common.Status;
import Database.DBControl;

public class Drone {
    private UUID id;
    private Status status = Status.AVAILABLE;
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
        DBControl.registerObjectToDB(this);
    }

    public double getSpeed(){
        return this.speed;
    }

    public double getMaxLoad(){
        return this.maxLoad;
    }

    public Status getStatus(){
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

    public boolean useBattery(double usage){
        if (usage >= this.batteryStatus) {
            this.status = Status.EMPTY;
            return false; //Not successful
        } else {
            this.batteryStatus -= usage;
            return true;
        }
    }

    public void chargeBattery(){
        this.batteryStatus = 1.0;
    }

    public boolean assingOrder(Order order){
        if (order.getWeight() > this.maxLoad || this.status != Status.AVAILABLE ){
            System.out.println("Can't assign order to this drone");
            return false; // This means operation was not done successfully
        } else {
            this.currentOrder = order;
            this.status = Status.BUSY;
            order.assignDrone(this);
            return true; //This means success
        }
    }

    public void deactivateDrone(){
        this.deactivateDrone("");
    }

    public void deactivateDrone(String reason){
        if (this.status == Status.BUSY){
            System.out.println("Trying to cancel an active drone");
        }
        this.status = Status.DEACTIVATED;
        this.accident = reason;
        this.currentOrder = null;
        this.currentPosition = null;
    }

    public void orderCompleted(){
        if (this.status == Status.DEACTIVATED) {
            return;
        }
        if (this.batteryStatus <= 0){
            this.status = Status.EMPTY;
        } else {
            this.status = Status.AVAILABLE;
        }
        this.currentOrder = null;
    }

}
