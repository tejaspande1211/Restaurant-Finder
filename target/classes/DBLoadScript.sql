CREATE DATABASE restaurantdb;
CREATE TABLE restaurant (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    location VARCHAR(255) NOT NULL,
    opening_time TIME NOT NULL,
    closing_time TIME NOT NULL
);

CREATE TABLE item (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  price INT NOT NULL,
  restaurant_id BIGINT,
  CONSTRAINT fk_restaurant
      FOREIGN KEY (restaurant_id)
          REFERENCES restaurant(id)
          ON DELETE CASCADE
);