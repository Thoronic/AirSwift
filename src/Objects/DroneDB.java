package Objects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Common.Position;
import Common.Status;
import Database.DatabaseConnection;

public class DroneDB {
    static Connection connection = DatabaseConnection.getConnection();

    private DroneDB(){}

    public static void addDrone(Position pos, double maxLoad, double speed) {
        
        String insertSql = "INSERT INTO Drones (PositionX, PositionY, PositionZ, Status, MaxLoad, Speed, Battery, OrderID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, NULL)";

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
        }
    }

    public static int getFreeDrone(double weight, Position pos){
        String sql = "SELECT * FROM Drones WHERE Status = ? AND MaxLoad >= ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, Status.AVAILABLE.toString());
            preparedStatement.setDouble(2, weight);
            ResultSet resultSet = preparedStatement.executeQuery();

            int bestDrone = -1;
            double bestDist = Double.POSITIVE_INFINITY;
            
            while (resultSet.next()){
                Position dronePos = new Position(resultSet.getDouble("PositionX"), resultSet.getDouble("PositionY"), resultSet.getDouble("PositionZ"));
                if (dronePos.getDistance(pos) <= bestDist){
                    bestDist = dronePos.getDistance(pos);
                    bestDrone = resultSet.getInt("ID");
                }
            }
            return bestDrone;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void assignDronetoOrder(int droneID, int orderID) {
        String sql = "UPDATE Drones SET Status = ?, OrderID = ? WHERE ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, Status.BUSY.toString());
            preparedStatement.setInt(2, orderID);
            preparedStatement.setInt(3, droneID);
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Status updated successfully.");
            } else {
                System.out.println("No rows were updated. Drone not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        OrderDB.assignOrdertoDrone(orderID, droneID);
    }

    public static void setNewDronePosition(int droneID, Position pos) {
        String sql = "UPDATE Drones SET PositionX = ?, PositionY = ?, PositionZ = ? WHERE ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setDouble(1, pos.getX());
            preparedStatement.setDouble(2, pos.getY());
            preparedStatement.setDouble(3, pos.getZ());
            preparedStatement.setInt(4, droneID);
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

    public static Position getDronePos(int droneID){
        String sql = "SELECT * FROM Drones WHERE ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, droneID);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Position pos = new Position(resultSet.getDouble("PositionX"), resultSet.getDouble("PositionY"), resultSet.getDouble("PositionZ"));
            return pos;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Position();
    }

    public static double getDroneBattery(int droneID){
        String sql = "SELECT * FROM Drones WHERE ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, droneID);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getDouble("Battery");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Double.NEGATIVE_INFINITY;
    }

    public static void updateDroneBattery(int droneID, double amount) { //pass a negative amount to substract battery power
        double newBattery = getDroneBattery(droneID) + amount;
        Status newStatus = getDroneStatus(droneID);
        if (newBattery <= 0){
            newStatus = Status.EMPTY;
        }
        String sql = "UPDATE Drones SET Battery = ?, Status = ? WHERE ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setDouble(1, newBattery);
            preparedStatement.setString(2, newStatus.toString());
            preparedStatement.setInt(3, droneID);
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

    public static Status getDroneStatus(int droneID){
        String sql = "SELECT * FROM Drones WHERE ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, droneID);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            switch (resultSet.getString("Status")) {
                case "AVAILABLE":
                    return Status.AVAILABLE;
                case "BUSY":
                    return Status.BUSY;
                case "EMPTY":
                    return Status.EMPTY;
                default:
                    return Status.DEACTIVATED;
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Status.DEACTIVATED;
    }

    public static double getDroneSpeed(int droneID){
        String sql = "SELECT * FROM Drones WHERE ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, droneID);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getDouble("Speed");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Double.NEGATIVE_INFINITY;
    }

    public static double getDroneMaxLoad(int droneID){
        String sql = "SELECT * FROM Drones WHERE ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, droneID);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getDouble("MaxLoad");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Double.NEGATIVE_INFINITY;
    }

    public static int getOrderFromDrone(int droneID){
        String sql = "SELECT * FROM Drones WHERE ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, droneID);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return resultSet.getInt("OrderID");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void setOrderCompleted(int droneID) {
        Status status = getDroneStatus(droneID);
        if (status == Status.BUSY) {
            status = Status.AVAILABLE;
        }
        String sql = "UPDATE Drones SET Status = ?, OrderID = null WHERE ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, status.toString());
            preparedStatement.setInt(2, droneID);
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

    public static void registerAccident(int droneID, String accident){
        String insertSql = "INSERT INTO Accidents (Accident, PositionX, PositionY, PositionZ, DroneID) VALUES (?, ?, ?, ?, ?)";
        Position pos = getDronePos(droneID);
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
            preparedStatement.setString(1, accident);
            preparedStatement.setDouble(2, pos.getX());
            preparedStatement.setDouble(3, pos.getY());
            preparedStatement.setDouble(4, pos.getZ());
            preparedStatement.setInt(5, droneID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        deactivateDrone(droneID);
        OrderDB.abortOrder(getOrderFromDrone(droneID));
    }

    public static void deactivateDrone(int droneID){
        String sql = "UPDATE Drones SET Status = ? WHERE ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, Status.DEACTIVATED.toString());
            preparedStatement.setInt(2, droneID);
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
}
