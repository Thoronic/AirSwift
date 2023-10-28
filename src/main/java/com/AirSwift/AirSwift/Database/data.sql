INSERT INTO Customers (Name, Handle, PositionX, PositionY, PositionZ) VALUES
('John Doe', 'JDo', 1.0, 2.0, 3.0),
('Alice Smith', 'i_miss_him', 2.5, 3.5, 1.0);

INSERT INTO Orders (Status, StartPosX, StartPosY, StartPosZ, EndPosX, EndPosY, EndPosZ, Weight, CustomerID, DroneID) VALUES
-- Insertion statements for the Orders table, should only be done in runtime

INSERT INTO Drones (PositionX, PositionY, PositionZ, Status, MaxLoad, Speed, Battery, OrderID) VALUES
(3.0, 10.0, 5.5,'AVAILABLE', 2000, 5, 1.0, NULL);
--(double, double, double, String (look in Position.java), double in grams, double in m/s, 1.0 = 100%, NULL )
-- Insertion statements for the Drones table

INSERT INTO Accidents (Accident, PositionX, PositionY, PositionZ, DroneID)
-- DB for Accidents, it should be empty on start