package Objects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Common.Position;
import Common.Status;
import Database.DatabaseConnection;

public class OrderDB {
    private OrderDB(){}
    static Connection connection = DatabaseConnection.getConnection();

    public static void addOrder(Position startPos, Position endPos, double weight) {
        String insertSql = "INSERT INTO Orders (Status, StartPosX, StartPosY, StartPosZ, EndPosX, EndPosY, EndPosZ, Weight, CustomerID, DroneID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, NULL, NULL)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
            preparedStatement.setString(1, Status.OPEN.toString());
            preparedStatement.setDouble(2, startPos.getX());
            preparedStatement.setDouble(3, startPos.getY());
            preparedStatement.setDouble(4, startPos.getZ());
            preparedStatement.setDouble(5, endPos.getX());
            preparedStatement.setDouble(6, endPos.getY());
            preparedStatement.setDouble(7, endPos.getZ());
            preparedStatement.setDouble(8, weight);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /*public Position getStartPos(){
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
    }*/
}
