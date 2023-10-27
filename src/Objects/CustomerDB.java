package Objects;

import Common.Position;
import Database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public static Position getCustomerPos(int customerID){
        String sql = "SELECT * FROM Customers WHERE ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, customerID);
            ResultSet resultSet = preparedStatement.executeQuery();

            Position pos = new Position(resultSet.getDouble("PositionX"), resultSet.getDouble("PositionY"), resultSet.getDouble("PositionZ"));
            return pos;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Position();
    }
}
