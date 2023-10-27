package Objects;

import Common.Position;
import Database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerDB{
    private CustomerDB(){}
    static Connection connection = DatabaseConnection.getConnection();

    public static void addCustomer(String name, String handle, Position pos) {
        String insertSql = "INSERT INTO Customers (Name, PositionX, PositionY, PositionZ) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, handle);
            preparedStatement.setDouble(3, pos.getX());
            preparedStatement.setDouble(4, pos.getY());
            preparedStatement.setDouble(5, pos.getZ());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*public UUID getID(){
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
    } */
}
