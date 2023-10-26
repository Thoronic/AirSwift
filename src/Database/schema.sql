USE DroneCompany
  
-- Customers Table
CREATE TABLE Customers (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(255) NOT NULL,
    PositionX DECIMAL(10, 6) NOT NULL,
    PositionY DECIMAL(10, 6) NOT NULL,
    PositionZ DECIMAL(10, 6) NOT NULL
);

-- Orders Table
CREATE TABLE Orders (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    StartPosX DECIMAL(10, 6) NOT NULL,
    StartPosY DECIMAL(10, 6) NOT NULL,
    StartPosZ DECIMAL(10, 6) NOT NULL,
    EndPosX DECIMAL(10, 6) NOT NULL,
    EndPosY DECIMAL(10, 6) NOT NULL,
    EndPosZ DECIMAL(10, 6) NOT NULL,
    Weight INT NOT NULL,
    CustomerID INT,
    DroneID INT,
    FOREIGN KEY (CustomerID) REFERENCES Customers(ID),
    FOREIGN KEY (DroneID) REFERENCES Drones(ID)
);

-- Drones Table
CREATE TABLE Drones (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    PositionX DECIMAL(10, 6) NOT NULL,
    PositionY DECIMAL(10, 6) NOT NULL,
    PositionZ DECIMAL(10, 6) NOT NULL,
    Status VARCHAR(255) NOT NULL,
    BatteryStatus DECIMAL(5, 2) NOT NULL,
    OrderID INT,
    FOREIGN KEY (OrderID) REFERENCES Orders(ID)
);
