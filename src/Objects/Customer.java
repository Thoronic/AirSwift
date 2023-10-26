package Objects;

import Common.Position;

import java.util.UUID;

public class Customer{
    private String name;
    private UUID id;
    private Position pos;

    public Customer(String name, Position pos){
        this.name = name;
        this.id = UUID.randomUUID();
        this.pos = pos;
    }

    public UUID getID(){
        return this.id;
    }

    public void changePosition(Position pos){
        this.pos = pos;
    }

    public void changePosition(double deltaX, double deltaY, double deltaZ){
        this.pos.movePosition(deltaX, deltaY, deltaZ);
    }

    public Position getPosition(){
        return this.pos;
    }

    public void printCustomer(){
        System.out.println("Name: " + name);
        System.out.println("ID: " + id);
        System.out.println("Current Position: "+ this.pos.toString());
        //System.out.println("Current Position: (" + pos.getX() + "," + pos.getY() + "," + pos.getZ() + ")");
    }
}
