CREATE TABLE IF NOT EXISTS Customer(
	CustomerID int PRIMARY KEY AUTO_INCREMENT,
	FirstName VARCHAR(255),
	LastName VARCHAR(255),
	Username VARCHAR(255),
	Password VARCHAR(255),
	EmailAddress VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS Orders(
    OrderID INT PRIMARY KEY AUTO_INCREMENT,
    ShippedFrom VARCHAR(255),
--    CustomerID INT FOREIGN KEY REFERENCES TO Customer(CustomerID),
    CreatedAt DATE,
    AddressCountry VARCHAR(255),
    AddressCity VARCHAR(255),
    AddressCounty VARCHAR(255),
    AddressStreetAddress VARCHAR(255)
);