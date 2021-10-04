create database smartportables;

create table registration(
	userId int not null primary key auto_increment,
	userName varchar(40),
	password varchar(40),
    rePassword varchar(40),
    userType varchar(40)
);

Create table customerOrders (
	orderId integer,
	userName varchar(40),
	orderName varchar(40),
	orderPrice double,
    streetAddress varchar(40),
    cityAddress varchar(40),
    stateAddress varchar(40),
    zipcode varchar(40),
    customerName varchar(40),
    hasWarranty varchar(8),
	creditCardNo varchar(40),
    deliveryMethod varchar(40),
    orderDate varchar(12),
    deliveryDate varchar(12),
    maxOrderCancellationDate varchar(12),
	Primary key(OrderId, userName, orderName)
);

Create table orderPickup(
	orderId int,
    userName varchar(40),
    orderName varchar(40),
    pickupStore varchar(20),
    orderDate varchar(12),
    pickupDate varchar(20),
    primary key(orderId, userName, orderName)
);

Create table productDetails (
	productType varchar(20),
	productId varchar(20),
	productName varchar(40),
	productPrice double,
	productImage varchar(40),
	productManufacturer varchar(40),
	productCondition varchar(40),
	productDiscount double,
    productRebate varchar(10),
    productDescription varchar(255),
	Primary key(productId)
);

CREATE TABLE productAccessories (
	accessoriesId int not null primary key auto_increment,
    productName varchar(20),
    accessoriesName  varchar(20),
    accessoriesPrice double,
    accessoriesDescription varchar(40),
    accessoriesManufacturer varchar(40),
	accessoriesImage varchar(40),
    accessoriesCondition varchar(40),
	accessoriesDiscount double,
    accessoriesRebate varchar(10),
    
    FOREIGN KEY (productName) REFERENCES productDetails(productId) ON DELETE SET NULL
        ON UPDATE CASCADE,
    FOREIGN KEY (accessoriesName) REFERENCES productDetails(productId) ON DELETE SET NULL
        ON UPDATE CASCADE    
);

CREATE TABLE store (
	storeId varchar(80) not null primary key,
    street varchar(80),
    city varchar(80),
    state varchar(80),
    zipcode varchar(6)
);