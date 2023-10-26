package Objects;

import java.util.UUID;

import Common.Position;
import Database.DBControl;

public class Order {
    private UUID id;
    private Position startPos;
    private Position endPos;
    private Drone assignedDrone = null;
    private double weight;

    public Order(Position startPos, Customer customer, double weight){
        this.id = UUID.randomUUID();
        this.startPos = startPos;
        this.endPos = customer.getPosition();
        this.weight = weight;
        DBControl.registerObjectToDB(this);
    }

    public Position getStartPos(){
        return this.startPos;
    }

    public Position getEndPos(){
        return this.endPos;
    }

    public UUID getID(){
        return this.id;
    }

    public double getWeight(){
        return this.weight;
    }

    public void assignDrone(Drone drone){
        this.assignedDrone = drone;
    }

    public void completeOrder(){
        this.assignedDrone = null;
    }

}
