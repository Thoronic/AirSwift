INSERT INTO Customers (Name, Handle, PositionX, PositionY, PositionZ) VALUES
('John Doe', 'JDo', 1.0, 2.0, 3.0),
('Alice Smith', 'i miss him', 2.5, 3.5, 1.0);

INSERT INTO Orders (Status, StartPosX, StartPosY, StartPosZ, EndPosX, EndPosY, EndPosZ, Weight, CustomerID, DroneID) VALUES
-- Insertion statements for the Orders table

INSERT INTO Drones (PositionX, PositionY, PositionZ, Status, MaxLoad, Speed, Battery, OrderID) VALUES
-- Insertion statements for the Drones table

INSERT INTO Accidents (Accident, PositionX, PositionY, PositionZ, DroneID)