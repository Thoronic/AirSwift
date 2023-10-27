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

    public static void addOrder(Position startPos, int customerID, double weight) {
        Position customerPos = CustomerDB.getCustomerPos(customerID);
        String insertSql = "INSERT INTO Orders (Status, StartPosX, StartPosY, StartPosZ, EndPosX, EndPosY, EndPosZ, Weight, CustomerID, DroneID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, NULL)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
            preparedStatement.setString(1, Status.OPEN.toString());
            preparedStatement.setDouble(2, startPos.getX());
            preparedStatement.setDouble(3, startPos.getY());
            preparedStatement.setDouble(4, startPos.getZ());
            preparedStatement.setDouble(5, customerPos.getX());
            preparedStatement.setDouble(6, customerPos.getY());
            preparedStatement.setDouble(7, customerPos.getZ());
            preparedStatement.setDouble(8, weight);
            preparedStatement.setInt(9, customerID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void assignOrdertoDrone(int orderID, int droneID){
        String sql = "UPDATE Orders SET Status = ?, DroneID = ? WHERE ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, Status.IN_DELIVERY.toString());
            preparedStatement.setInt(2, droneID);
            preparedStatement.setInt(3, orderID);
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Status updated successfully.");
            } else {
                System.out.println("No rows were updated. Drone not found.");
            }
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
