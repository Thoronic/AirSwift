-- Customers Table
CREATE TABLE Customers (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(255) NOT NULL,
    Handle VARCHAR(255) NOT NULL,
    PositionX DECIMAL(10, 6) NOT NULL,
    PositionY DECIMAL(10, 6) NOT NULL,
    PositionZ DECIMAL(10, 6) NOT NULL
);

-- Drones Table
CREATE TABLE Drones (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    PositionX DECIMAL(10, 6) NOT NULL,
    PositionY DECIMAL(10, 6) NOT NULL,
    PositionZ DECIMAL(10, 6) NOT NULL,
    Status VARCHAR(255) NOT NULL,
    MaxLoad DECIMAL(10, 6) NOT NULL,
    Speed DECIMAL(10, 6) NOT NULL,
    Battery DECIMAL(10, 6) NOT NULL    
);

-- Orders Table
CREATE TABLE Orders (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    Status VARCHAR(255) NOT NULL,
    StartPosX DECIMAL(10, 6) NOT NULL,
    StartPosY DECIMAL(10, 6) NOT NULL,
    StartPosZ DECIMAL(10, 6) NOT NULL,
    EndPosX DECIMAL(10, 6) NOT NULL,
    EndPosY DECIMAL(10, 6) NOT NULL,
    EndPosZ DECIMAL(10, 6) NOT NULL,
    Weight DECIMAL(10,6) NOT NULL,
    CustomerID INT,
    DroneID INT,
    FOREIGN KEY (CustomerID) REFERENCES Customers(ID),
    FOREIGN KEY (DroneID) REFERENCES Drones(ID)
);

ALTER TABLE Drones
ADD COLUMN OrderID INT,
ADD FOREIGN KEY (OrderID) REFERENCES Orders(ID);

-- Accidents Table
CREATE TABLE Accidents (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    Accident VARCHAR(255) NOT NULL,
    StartPosX DECIMAL(10, 6) NOT NULL,
    StartPosY DECIMAL(10, 6) NOT NULL,
    StartPosZ DECIMAL(10, 6) NOT NULL,
    DroneID INT,
    FOREIGN KEY (DroneID) REFERENCES Drones(ID)
)