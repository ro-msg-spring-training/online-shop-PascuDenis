CREATE TABLE Customer(
	CustomerID INT PRIMARY KEY,
	FirstName VARCHAR(255),
	LastName VARCHAR(255),
	Username VARCHAR(255),
	Password VARCHAR(255),
	EmailAddress VARCHAR(255)
);

CREATE TABLE Location (
    LocationID INT PRIMARY KEY,
    Name VARCHAR(255),
    AddressCountry VARCHAR(255),
    AddressCity VARCHAR(255),
    AddressCounty VARCHAR(255),
    AddressStreetAddress VARCHAR(255)
);

CREATE TABLE Revenue(
    RevenueID INT PRIMARY KEY,
    LocationID INT,
    Date_ DATE,
    Sum_ INT,
    FOREIGN KEY (LocationID) REFERENCES Location(LocationID)
);

CREATE TABLE Supplier(
    SupplierID INT PRIMARY KEY,
    Name VARCHAR(255)
);

CREATE TABLE ProductCategory(
    ProductCategoryID INT PRIMARY KEY,
    Name VARCHAR(255),
    Description VARCHAR(255)
);

CREATE TABLE Product (
    ProductID INT PRIMARY KEY,
    Name VARCHAR(255),
    Description VARCHAR(255),
    Price FLOAT,
    Weight FLOAT,
    ProductCategoryID INT,
    SupplierID INT,
    ImageUrl VARCHAR(255),
    FOREIGN KEY (ProductCategoryID) REFERENCES ProductCategory(ProductCategoryID),
    FOREIGN KEY (SupplierID) REFERENCES Supplier(SupplierID)
);

CREATE TABLE Stock(
    ProductID INT,
    LocationID INT,
    Quantity INT,
    FOREIGN KEY (ProductID) REFERENCES Product(ProductID),
    FOREIGN KEY (LocationID) REFERENCES Location(LocationID),
    CONSTRAINT StockID PRIMARY KEY (ProductID, LocationID)
);

CREATE TABLE Order_ (
    OrderID INT PRIMARY KEY AUTO_INCREMENT,
    ShippedFrom INT,
    CustomerID INT,
    CreatedAt DATE,
    AddressCountry VARCHAR(255),
    AddressCity VARCHAR(255),
    AddressCounty VARCHAR(255),
    AddressStreetAddress VARCHAR(255),
    FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID),
    FOREIGN KEY (ShippedFrom) REFERENCES Location(LocationID)
);

CREATE TABLE OrderDetail(
    OrderID INT,
    ProductID INT,
    Quantity INT,
    FOREIGN KEY (OrderID) REFERENCES Order_(OrderID),
    FOREIGN KEY (ProductID) REFERENCES Product(ProductID)
);

