package Objects;

import java.util.UUID;

import Common.Position;

public class Drone {
    private UUID id;
    private String status;
    private double maxLoad;
    private double speed = 10.0;
    private Order currentOrder = null;
    private Position currentPosition;
    private String accident = "";

    public Drone(Position pos, double maxLoad){
        this.id = UUID.randomUUID();
        this.currentPosition = pos;
        this.maxLoad = maxLoad;
    }

    

}
