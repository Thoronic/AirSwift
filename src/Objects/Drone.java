package Objects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Common.Position;
import Common.Status;
import Database.DatabaseConnection;

public class Drone {

    private Drone(){}

    public static void addDrone(Position pos, double maxLoad, double speed) {
        Connection connection = DatabaseConnection.getConnection();
        String insertSql = "INSERT INTO Customers (PositionX, PositionY, PositionZ, Status, MaxLoad, Speed, Battery, OrderID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, NULL)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
            preparedStatement.setDouble(1, pos.getX());
            preparedStatement.setDouble(2, pos.getY());
            preparedStatement.setDouble(3, pos.getZ());
            preparedStatement.setString(4, Status.AVAILABLE.toString());
            preparedStatement.setDouble(5, maxLoad);
            preparedStatement.setDouble(6, speed);
            preparedStatement.setDouble(6, 1.0);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /*public double getSpeed(){
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
    }*/

}
