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
    customerName varchar(40),
    customerAddress varchar(80),
    creditCardNo integer,
    purchaseDate varchar(20),
    shipDate varchar(20),
	productName varchar(40),
    productType varchar(40),
    productQuantity integer,
	productPrice double,
    shippingCost double,
    discountPrice double,
    orderTotal double,
    isWarrantyIncluded bool,
    warrantyPrice double,
    deliveryMethod varchar(40),
    maxPickupDate varchar(20),
    pickupStoreName varchar(40),
    maxCancellationDate varchar(20),
	Primary key(orderId, userName, productName)
);

Create table productDetails (
	productType varchar(40),
	productId varchar(80),
	productName varchar(40),
	productPrice double,
	productImage varchar(40),
	productManufacturer varchar(40),
	productCondition varchar(40),
	productDiscount double,
    manufacturerRebate integer,
    productHasWarranty bool,
    productWarranty double,
    productDescription varchar(255),
    numberOfAvailableProducts integer,
    numberOfItemsSold integer,
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

create table transactions (
	transactionId int not null primary key auto_increment,
	orderId integer,
    userName varchar(80),
    streetAddress varchar(40),
    cityAddress varchar(40),
    stateAddress varchar(40),
    zipcode varchar(40),
    creditCardNo varchar(12),
    deliveryMethod varchar(40),
    pickupStoreName varchar(40),
    orderDate varchar(12),
    deliveryDate varchar(12),
    maxOrderCancellationDate varchar(12),
    maxPickupDate varchar(12),
    
    FOREIGN KEY (orderId) REFERENCES customerOrders(orderId) ON DELETE SET NULL
        ON UPDATE CASCADE
);

create table customer (
	customerId int not null auto_increment,
    customerName varchar(80),
    street varchar(80), 
    city varchar(80),
    state varchar(80),
    zipcode varchar(80),
    
    primary key(customerId, customerName)
);

create table product (
	productId varchar(80),
    productName varchar(80),
    image varchar(80),
    retailer varchar(80),
    productCondition varchar(80),
    productPrice varchar(80),
    productType varchar(80),
    discount varchar(80),
    
    primary key(productId)
);