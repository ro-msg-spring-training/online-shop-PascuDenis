ALTER TABLE Revenue ALTER COLUMN Date_ DATE;

INSERT INTO Customer(CustomerID, FirstName, LastName, Username, Password, EmailAddress) VALUES
(1, 'FirstName01', 'LastName01', 'Username01', 'Password01', 'Email01'),
(2, 'FirstName02', 'LastName02', 'Username02', 'Password02', 'Email02'),
(3, 'FirstName03', 'LastName03', 'Username03', 'Password03', 'Email03'),
(4, 'FirstName04', 'LastName04', 'Username04', 'Password04', 'Email04'),
(5, 'FirstName05', 'LastName05', 'Username05', 'Password05', 'Email05');

INSERT INTO Location(LocationID, Name, AddressCountry, AddressCity, AddressCounty, AddressStreetAddress) VALUES
(1, 'Name01','AddressCountry01','AddressCity01','AddressCounty01','AddressStreetAddress01'),
(2, 'Name02','AddressCountry02','AddressCity02','AddressCounty02','AddressStreetAddress02'),
(3, 'Name03','AddressCountry03','AddressCity03','AddressCounty03','AddressStreetAddress03'),
(4, 'Name04','AddressCountry04','AddressCity04','AddressCounty04','AddressStreetAddress04'),
(5, 'Name05','AddressCountry05','AddressCity05','AddressCounty05','AddressStreetAddress05');

INSERT INTO Revenue(RevenueID, LocationID, Date_, Sum_) VALUES
(1, 1,'2015-11-13',100),
(2, 2,'2015-11-13',200),
(3, 3,'2015-11-13',300),
(4, 4,'2015-11-13',4000),
(5, 5,'2015-11-13',5680);

INSERT INTO Supplier(SupplierID, Name) VALUES
(1, 'Name01'),
(2, 'Name02'),
(3, 'Name03'),
(4, 'Name04'),
(5, 'Name05');


INSERT INTO ProductCategory(ProductCategoryID, Name, Description) VALUES
(1, 'Name01','Description01'),
(2, 'Name02','Description02'),
(3, 'Name03','Description03'),
(4, 'Name04','Description04'),
(5, 'Name05','Description05');

INSERT INTO Product(ProductID, Name,Description, Price, Weight, ProductCategoryID, SupplierID, ImageUrl) VALUES
(1, 'Name01','Description01', 12,23,1,4,'Url01'),
(2, 'Name02','Description02', 12,25,2,3,'Url02'),
(3, 'Name03','Description03', 23,26,3,2,'Url03'),
(4, 'Name04','Description04', 54,31,1,5,'Url04'),
(5, 'Name05','Description05', 63,22,1,1,'Url05');

INSERT INTO Stock(ProductID,LocationID, Quantity) VALUES
(1,1,65),
(2,2,55),
(3,2,44),
(4,3,32),
(1,4,12);

INSERT INTO Order_(OrderID, ShippedFrom, CustomerID, CreatedAt, AddressCountry, AddressCity, AddressCounty, AddressStreetAddress) VALUES
(1, 1, 2, '2015-11-13', 'AddressCountry01', 'AddressCity01', 'AddressCounty01', 'AddressStreetAddress01'),
(2, 1, 3, '2015-11-13', 'AddressCountry02', 'AddressCity02', 'AddressCounty02', 'AddressStreetAddress02'),
(3, 1, 1, '2015-11-13', 'AddressCountry03', 'AddressCity03', 'AddressCounty03', 'AddressStreetAddress03'),
(4, 1, 5, '2015-11-13', 'AddressCountry04', 'AddressCity04', 'AddressCounty04', 'AddressStreetAddress04'),
(5, 2, 1, '2015-11-13', 'AddressCountry05', 'AddressCity05', 'AddressCounty05', 'AddressStreetAddress05');


INSERT INTO OrderDetail(OrderID, ProductID, Quantity) VALUES
(5,1,11),
(4,2,22),
(2,2,33),
(4,3,44),
(1,4,55);
