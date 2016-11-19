DROP DATABASE IF EXISTS	FishTankTest;
CREATE DATABASE FishTankTest;

USE FishTankTest;

CREATE TABLE `Fish`(
	`id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL,
    `scientificName` VARCHAR(100),
    `waterType` VARCHAR(50),
    `pictureURL` VARCHAR(200),
    PRIMARY KEY (`id`)
);

-- Write some insert statements for testing purposes
-- But gotta do watertype first becuase of foriegn key constraint

INSERT INTO `Fish` (`name`, `scientificName`, `waterType`, `pictureURL`)
			 VALUES("Goldfish", "Goldyfishicus", "Freshwater", "example.com/fish.png"),
					("Sharknado", "Sharky", "Freshwater", "example.com/shark.png"),
				   ("Redfish", "Bloody", "Freshwater", "example.com/redfish.png"),
				   ("Bluefish", "Blue Jolly Rancher", "Saltwater", "example.com/bluefish.png"),
				   ("Go Fish", "Goldyfishicus", "Saltwater", "example.com/gofish.png"),
				   ("Generic Fish", "fish", "Saltwater", "example.com/generic.png"),
				   ("Dope ass fish", "Fresh 2 Death", "Saltwater", "example.com/dope.png");