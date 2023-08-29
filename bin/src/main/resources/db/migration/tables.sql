CREATE DATABASE sundaratravels;
USE sundaratravels;

CREATE TABLE route (
    id INT PRIMARY KEY AUTO_INCREMENT,
    from_location VARCHAR(100) NOT NULL,
    to_location VARCHAR(100) NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE bus (
    id INT PRIMARY KEY AUTO_INCREMENT,
    bus_no VARCHAR(12) UNIQUE NOT NULL,
    departure_time TIME NOT NULL,
    arrival_time TIME NOT NULL,
    schedule_id INT NOT NULL,
    is_ac BOOLEAN NOT NULL,
    is_active BOOLEAN NOT NULL,
    route_id INT NOT NULL,
    FOREIGN KEY (route_id) REFERENCES route(id) -- Adding foreign key constraint
);

ALTER TABLE bus MODIFY COLUMN is_active BOOLEAN DEFAULT NULL;

