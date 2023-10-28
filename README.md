# DB-for-drone-system

This system provides the back end for the drone control system "AirSwift".

## Setup Database

To set up the database for the first time, make sure mysql/mariaDB is installed on your system.
Continue to start mysql:
`$ sudo systemctl start mysql
$ sudo systemctl enable mysql
$ sudi mysql -u root`

Now you should be in the shell of mysql/mariaDB. Create the database and load the preconfigured tables and data:
`$ CREATE DATABASE DroneCompany
$ USE DroneCompany
$ source full/path/to/Database/schema.sql
$ source full/path/to/Database/data.sql`

## Compile Java

To compile the back end we use maven:
`$ mvn compile
$ mvn exec:java`

## Functions provided to the front end

Currently there are 3 functions freely provided to the frontend. There are multiple more available directly on the database that could be called as well, if needed be.

`public static String registerCustomer(String Name, String handle, Position pos);`
This function takes the name, handle and postion of a new customer. First it checks if the handel is taken already, if not, then the new customer is registered.

`public static String registerDrone(double maxLoad, double speed);`
This function registers a new drone with its maximal load and speed.

`public static String newOrder(String customerHandle, Position startPos, Double weight);`
This function creates a new order in the system. First it checks if the customer is already registered and returns an error if not. Then it checks if any drones which can carry the requested weight are available. If yes, then it chooses the closest drone to the starting position and starts the delivery process.