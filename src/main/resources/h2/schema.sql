CREATE TABLE IF NOT EXISTS location_master (location_id INT, location_name VARCHAR(255), location_city VARCHAR(255), location_state VARCHAR(255), location_zipCode VARCHAR(255),  PRIMARY KEY (location_id));
INSERT INTO location_master (location_id, location_name, location_city, location_state, location_zipCode) VALUES (1,'HYD','HYD','TG','94536'), (2,'PUNE','PUNE','MH','94536');

CREATE TABLE IF NOT EXISTS user_master (user_id INT, user_name VARCHAR(255), password VARCHAR(255), role VARCHAR(255), PRIMARY KEY (user_id));
INSERT INTO user_master (user_id, user_name, password, role) VALUES (1,'RAJU','RAJU', 'admin'), (2,'PRAGYAN','PRAGYAN','admin');

CREATE TABLE IF NOT EXISTS flight_information_master
(flight_number VARCHAR(255), airline VARCHAR(255),
arrival_city VARCHAR(255), arrival_date DATE, arrival_time VARCHAR(255),
departure_city VARCHAR(255), departure_date DATE, departure_time VARCHAR(255),
first_class_seat INT, first_class_seat_fare DOUBLE,
business_class_seat INT, business_class_seat_fare DOUBLE,
available_first_class_seats INT, available_business_class_seats INT,
max_seats INT, is_active INT,
PRIMARY KEY (flight_number));

CREATE TABLE IF NOT EXISTS booking_information_master
(booking_id VARCHAR(255), customer_email VARCHAR(255), no_of_passengers INT,
source_city VARCHAR(255), destination_city VARCHAR(255),
total_fare DOUBLE, seat_numbers VARCHAR(255), credit_card_info VARCHAR(255),
flight_number VARCHAR(255),class_type VARCHAR(255),
PRIMARY KEY (booking_id));