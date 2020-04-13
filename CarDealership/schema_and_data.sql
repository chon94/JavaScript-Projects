DROP DATABASE IF EXISTS CarDealership;

CREATE DATABASE CarDealership;

-- CREATE DATABASE IF NOT EXISTS CarDealership;

USE CarDealership;

CREATE TABLE `User` (
UserId INT PRIMARY KEY AUTO_INCREMENT,
FirstName varchar(40) NOT NULL,
LastName varchar(40) NOT NULL,
Email varchar(100) NOT NULL,
`Password` varchar(60) NOT NULL,
`Role` varchar(40)
);

CREATE TABLE ContactMessage(
	messageId INT PRIMARY KEY AUTO_INCREMENT,
    `Name` varchar(40),
    Email varchar(100),
    Phone char(10),
    Message VARCHAR(300) NOT NULL
);

CREATE TABLE Make (
MakeId INT PRIMARY KEY AUTO_INCREMENT,
Make varchar(30) NOT NULL
);

CREATE TABLE Model (
ModelId INT PRIMARY KEY AUTO_INCREMENT,
MakeId INT NOT NULL,
Model varchar(30) NOT NULL,
FOREIGN KEY fk_Model_Make (MakeId)
		REFERENCES Make(MakeId)
);

CREATE TABLE Special (
SpecialId INT PRIMARY KEY AUTO_INCREMENT,
Title varchar(40) NOT NULL,
`Description` varchar(1500)
);

CREATE TABLE Vehicle (
VehicleId INT PRIMARY KEY AUTO_INCREMENT,
ModelId INT NOT NULL,
BodyStyle varchar(40) NOT NULL,
Trans varchar(40) NOT NULL,
Color varchar(40) NOT NULL,
Interior varchar(40) NOT NULL,
Mileage varchar(40) NOT NULL,
VIN varchar(40) NOT NULL,
`Type` varchar(20) NOT NULL,
SalePrice INT NOT NULL,
MSRP INT NOT NULL,
`Description` varchar(800) NOT NULL,
Photo varchar(200) NOT NULL,
Featured BOOLEAN NOT NULL,
Sold BOOLEAN NOT NULL DEFAULT false,
`Year` INT NOT NULL,
FOREIGN KEY fk_Vehicle_Model (ModelId)
		REFERENCES Model(ModelId)
);

CREATE TABLE Purchase (
PurchaseId INT PRIMARY KEY AUTO_INCREMENT,
`Name` varchar(50) NOT NULL,
Phone varchar(20),
Email varchar(100),
Street1 varchar(100) NOT NULL,
Street2 varchar(100),
State varchar(5) NOT NULL,
City varchar(20) NOT NULL,
Zipcode varchar(20) NOT NULL,
PurchaseDate DATE NOT NULL,
PurchaseType varchar(20) NOT NULL,
UserId INT NOT NULL,
VehicleId INT NOT NULL,
PurchasePrice INT NOT NULL,

FOREIGN KEY fk_Purchase_User (UserId)
		REFERENCES `User`(UserId),
FOREIGN KEY fk_Purchase_Vehicle (VehicleId)
		REFERENCES Vehicle(VehicleId)
);