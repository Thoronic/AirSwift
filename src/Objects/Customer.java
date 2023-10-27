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

    public static void insertCustomerData(String name, double posX, double posY, double posZ) {
        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) {
            System.err.println("Failed to establish a database connection.");
            return;
        }

        String insertSql = "INSERT INTO Customers (Name, PositionX, PositionY, PositionZ) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setDouble(2, posX);
            preparedStatement.setDouble(3, posY);
            preparedStatement.setDouble(4, posZ);
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
