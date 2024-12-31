CREATE TABLE IF NOT EXISTS location_master (location_id INT, location_name VARCHAR(255), location_city VARCHAR(255), location_state VARCHAR(255), location_zipCode VARCHAR(255),  PRIMARY KEY (location_id));
INSERT INTO location_master
(location_id, location_name, location_city, location_state, location_zipCode)
VALUES
(1,'HYD','HYD','TG','94536'),
(2,'PUNE','PUNE','MH','94536'),
(3,'BANGALORE','BANGALORE','KA','94536'),
(4,'CHENNAI','CHENNAI','TN','94536'),
(5,'MUMBAI','MUMBAI','MH','94536'),
(6,'DELHI','DELHI','DL','94536'),
(7,'KOLKATA','KOLKATA','WB','94536'),
(8,'AHMEDABAD','AHMEDABAD','GJ','94536'),
(9,'JAIPUR','JAIPUR','RJ','94536'),
(10,'LUCKNOW','LUCKNOW','UP','94536');

CREATE TABLE IF NOT EXISTS user_master (user_id INT, user_name VARCHAR(255), password VARCHAR(255), role VARCHAR(255), PRIMARY KEY (user_id));
INSERT INTO user_master (user_id, user_name, password, role) VALUES
(1,'RAJU','RAJU', 'admin'),
(2,'PRAGYAN','PRAGYAN','admin'),
(3,'SHAILU','SHAILU','user'),
(4,'LAXMI','LAXMI','user'),
(5,'RAJ','RAJ','customer'),
(7,'RAJESH','RAJESH','customer'),
(8,'RAJIV','RAJIV','customer'),
(9,'RAJAT','RAJAT','customer'),
(10,'RAJAN','RAJAN','customer');

CREATE TABLE IF NOT EXISTS flight_information_master
(flight_number VARCHAR(255), airline VARCHAR(255),
arrival_city VARCHAR(255), arrival_date DATE, arrival_time VARCHAR(255),
departure_city VARCHAR(255), departure_date DATE, departure_time VARCHAR(255),
first_class_seat INT, first_class_seat_fare DOUBLE,
business_class_seat INT, business_class_seat_fare DOUBLE,
available_first_class_seats INT, available_business_class_seats INT,
max_seats INT, is_active INT,
PRIMARY KEY (flight_number));

INSERT INTO flight_information_master
(flight_number, airline, arrival_city , arrival_date, arrival_time, departure_city, departure_date, departure_time,
first_class_seat,first_class_seat_fare,business_class_seat, business_class_seat_fare, available_first_class_seats, available_business_class_seats,
max_seats, is_active) VALUES
('AI101','AIR INDIA','HYD','2025-06-01','10:00:00','PUNE','2025-06-01','12:00:00',50,10000,50,20000,50,50,100,1),
('AI102','AIR INDIA','HYD','2025-06-01','10:00:00','BANGALORE','2025-06-01','12:00:00',50,10000,50,20000,50,50,100,1),
('AI103','AIR INDIA','HYD','2025-06-01','10:00:00','DELHI','2025-06-01','12:00:00',50,10000,50,20000,50,50,100,1),
('AI104','AIR INDIA','HYD','2025-06-01','10:00:00','KOLKATA','2025-06-01','12:00:00',50,10000,50,20000,50,50,100,1),
('AI105','AIR INDIA','HYD','2025-06-01','10:00:00','CHENNAI','2025-06-01','12:00:00',50,10000,50,20000,50,50,100,1),
('AI106','AIR INDIA','HYD','2025-06-01','10:00:00','AHMEDABAD','2025-06-01','12:00:00',50,10000,50,20000,50,50,100,1),
('AI107','AIR INDIA','HYD','2025-06-01','10:00:00','LUCKNOW','2025-06-01','12:00:00',50,10000,50,20000,50,50,100,1),
('AI108','AIR INDIA','HYD','2025-06-01','10:00:00','JAIPUR','2025-06-01','12:00:00',50,10000,50,20000,50,50,100,1),
('AI109','AIR INDIA','HYD','2025-06-01','10:00:00','MUMBAI','2025-06-01','12:00:00',50,10000,50,20000,50,50,100,1),
('AI111','AIR INDIA','PUNE','2025-06-01','10:00:00','HYD','2025-06-01','12:00:00',50,10000,50,20000,50,50,100,1),
('AI121','AIR INDIA','MUMBAI','2025-06-01','10:00:00','HYD','2025-06-01','12:00:00',50,10000,50,20000,50,50,100,1),
('AI131','AIR INDIA','DELHI','2025-06-01','10:00:00','HYD','2025-06-01','12:00:00',50,10000,50,20000,50,50,100,1);


CREATE TABLE IF NOT EXISTS booking_information_master
(booking_id VARCHAR(255), customer_email VARCHAR(255), no_of_passengers INT,
source_city VARCHAR(255), destination_city VARCHAR(255),
total_fare DOUBLE, seat_numbers VARCHAR(255), credit_card_info VARCHAR(255),
flight_number VARCHAR(255),class_type VARCHAR(255), is_active INT,
PRIMARY KEY (booking_id));

INSERT INTO booking_information_master
(booking_id, customer_email, no_of_passengers, source_city, destination_city, total_fare, seat_numbers, credit_card_info, flight_number, class_type, is_active)
VALUES
('B101','rajuchinthala.rc@gmail.com', 2, 'HYD', 'PUNE', 20000, '1A,1B', '1234567890123456', 'AI121', 'first_class', 1),
('B102','rajuchinthala_89@gmail.com', 4, 'HYD', 'PUNE', 20000, '2A,2B,2C,2D', '1234567890123456', 'AI111', 'first_class',1),
('B104','rajuchinthala2009@gmail.com', 2, 'HYD', 'PUNE', 20000, '3A,3B', '1234567890123456', 'AI111', 'business_class',1),
('B103','rajuchinthala.89@gmail.com', 4, 'HYD', 'MUMBAI', 20000, '1A,1B,1C,1D', '1234567890123456', 'AI121', 'first_class',1),
('B105','rajuchinthala_rc@gmail.com', 2, 'HYD', 'DELHI', 20000, '1A,1B', '1234567890123456', 'AI131', 'business_class',1),
('B106','raju.chinthala@outlook.com', 4, 'HYD', 'DELHI', 20000, '2A,2B,2C,2D', '1234567890123456', 'AI131', 'first_class',1),
('B107','raju_chinthala@outlook.com', 2, 'PUNE', 'HYD', 20000, '1A,1B', '1234567890123456', 'AI101', 'business_class',1),
('B108','raju_chinthala@outlook.com', 2, 'BANGALORE', 'HYD', 20000, '1A,1B', '1234567890123456', 'AI102', 'business_class',1),
('B109','rajuchinthala@gmail.com.com',2, 'DELHI', 'HYD', 20000, '1A,1B', '1234567890123456', 'AI103', 'business_class',1),
('B110','rajuchinthala@gmail.com.com',2, 'KOLKATA', 'HYD', 20000, '1A,1B', '1234567890123456', 'AI104', 'business_class',1),
('B111','rajuchinthala@gmail.com.com',2, 'CHENNAI', 'HYD', 20000, '1A,1B', '1234567890123456', 'AI105', 'business_class',1),
('B112','rajuchinthala@gmail.com.com',2, 'AHMEDABAD', 'HYD', 20000, '1A,1B', '1234567890123456', 'AI106', 'business_class',1),
('B113','rajuchinthala@gmail.com.com',2, 'LUCKNOW', 'HYD', 20000, '1A,1B', '1234567890123456', 'AI107', 'business_class',1),
('B114','rajuchinthala@gmail.com.com',2, 'JAIPUR', 'HYD', 20000, '1A,1B', '1234567890123456', 'AI108', 'business_class',1),
('B115','rajuchinthala@gmail.com.com',2, 'MUMBAI', 'HYD', 20000, '1A,1B', '1234567890123456', 'AI109', 'business_class',1);
