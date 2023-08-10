CREATE DATABASE sundaratravels;
use sundaratravels;
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
is_ac boolean not null,
is_active boolean not null,
route_id int not null
);