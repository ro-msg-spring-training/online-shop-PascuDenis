CREATE TABLE Orders(
    OrderID INT PRIMARY KEY AUTO_INCREMENT,
    ShippedFrom VARCHAR(255),
    CustomerID INT FOREIGN KEY REFERENCES Customer(ID),
    CreatedAt DATE,
    AddressCountry VARCHAR(255),
    AddressCity VARCHAR(255),
    AddressCounty VARCHAR(255),
    AddressStreetAddress VARCHAR(255)
);