package Objects;

import Common.Position;
import Database.DBControl;

import java.util.UUID;

public class Customer{
    private String name;
    private UUID id;
    private Position pos;

    public Customer(String name, Position pos){
        this.name = name;
        this.id = UUID.randomUUID();
        this.pos = pos;
        DBControl.registerObjectToDB(this);
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

}
