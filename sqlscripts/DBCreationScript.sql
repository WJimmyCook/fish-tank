DROP DATABASE IF EXISTS	FishTank;
CREATE DATABASE FishTank;

USE FishTank;

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


                        
