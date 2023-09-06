CREATE DATABASE IF NOT EXISTS sundaratravels;
USE sundaratravels;
CREATE TABLE `routes` (
                          `route_id` INT PRIMARY KEY AUTO_INCREMENT,
                          `from_location` VARCHAR(100) NOT NULL,
                          `to_location` VARCHAR(100) NOT NULL,
                          `base_price` DECIMAL(10 , 2 ) NOT NULL,
                          `is_active` BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE `buses` (
                         `bus_id` INT PRIMARY KEY AUTO_INCREMENT,
                         `bus_no` VARCHAR(12) UNIQUE NOT NULL,
                         `departure_time` TIME NOT NULL,
                         `arrival_time` TIME NOT NULL,
                         `capacity` INT NOT NULL,
                         `is_ac` BOOLEAN NOT NULL,
                         `is_active` BOOLEAN NOT NULL DEFAULT TRUE,
                         `route_id` INT NOT NULL,
                         `schedule_id` INT NOT NULL
);

CREATE TABLE `bus_schedules` (
                                 `schedule_id` INT PRIMARY KEY AUTO_INCREMENT,
                                 `monday` BOOLEAN NOT NULL DEFAULT TRUE,
                                 `tuesday` BOOLEAN NOT NULL DEFAULT TRUE,
                                 `wednesday` BOOLEAN NOT NULL DEFAULT TRUE,
                                 `thursday` BOOLEAN NOT NULL DEFAULT TRUE,
                                 `friday` BOOLEAN NOT NULL DEFAULT TRUE,
                                 `saturday` BOOLEAN NOT NULL DEFAULT TRUE,
                                 `sunday` BOOLEAN NOT NULL DEFAULT TRUE,
                                 `is_active` BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE `bookings` (
                            `booking_id` INT PRIMARY KEY AUTO_INCREMENT,
                            `bus_id` INT NOT NULL,
                            `travel_date` DATE NOT NULL,
                            `booked_seats` INT NOT NULL,
                            `is_active` BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE `tickets` (
                           `ticket_id` INT PRIMARY KEY AUTO_INCREMENT,
                           `booking_id` INT NOT NULL,
                           `travel_date` DATE NOT NULL,
                           `booked_seats` INT NOT NULL,
                           `total_price` DECIMAL(10 , 2 ) NOT NULL,
                           `is_active` BOOLEAN NOT NULL DEFAULT TRUE
);

ALTER TABLE `buses` ADD FOREIGN KEY (`route_id`) REFERENCES `routes` (`route_id`);

ALTER TABLE `buses` ADD FOREIGN KEY (`schedule_id`) REFERENCES `bus_schedules` (`schedule_id`);

ALTER TABLE `bookings` ADD FOREIGN KEY (`bus_id`) REFERENCES `buses` (`bus_id`);

ALTER TABLE `tickets` ADD FOREIGN KEY (`booking_id`) REFERENCES `bookings` (`booking_id`);

ALTER TABLE `routes`
    ADD CONSTRAINT `unique_route` UNIQUE (`from_location`, `to_location`);

ALTER TABLE `bookings`
ADD CONSTRAINT `unique_data_and_bus` UNIQUE (`bus_id`,`travel_date`);







