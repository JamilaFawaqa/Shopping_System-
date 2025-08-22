#Luai Hawi 1200203
#Jamila Fawaqa 1200435
###################################### NOTE : DON'T RUN THE WHOLE CODE ############################################
create database JLMart;
use JLMart;
create table customer (
	customerId int primary key not null AUTO_INCREMENT,
    customerName varchar (50) unique,
    customerAddress varchar(100),
    customerGender varchar(10),
    cusromerRating varchar(10),
    customerDateOfBirth date 
);
#table of a multi value attribute 
create table customerPhoneNum(
	customerId int,
    cPhoneNum varchar(15),
    primary key (customerId, cPhoneNum),
    foreign key (customerId) references customer (customerId)
);
create table employee (
	employeeId int primary key,
    empName varchar (50),
    empSalary int,
    activeHours int
);
#table of a multi value attribute 
create table empPhoneNum(
	employeeId int,
    empPhoneNum varchar(15),
    primary key (employeeId, empPhoneNum),
    foreign key (employeeId) references employee (employeeId)
);
create table account (
	username varchar(50) primary key,
    password varchar(20),
    customerId int,
    employeeId int,
    dateOfCreation date,
    foreign key (customerId) references customer (customerId),
    foreign key (employeeId) references employee (employeeId)
); 
create table department(
	depNum int primary key,
    employeeId int,
    foreign key (employeeId) references employee (employeeId)
);
create table product (
	productCode varchar(10) primary key,
    expDate date,
    proAmount int,
    proSection varchar(20),
    proPrice float,
    proName varchar(30),
    employeeId int,
    depNum int,
    foreign key (depNum) references department (depNum),
    foreign key (employeeId) references employee (employeeId)
    
);

create table payment(
	payId int primary key,
    currency int,
    amount int,
    customerId int,
    employeeId int,
    foreign key (customerId) references customer (customerId),
    foreign key (employeeId) references employee (employeeId)
);
create table sale (
	saleId int,
    discountPer int, 
    endDate date,
    productCode varchar(10),
    primary key (saleId, productCode), 
    foreign key (productCode) references product (productCode)
);
create table supplier (
	supplierId int,
    supplierName varchar (50),
    productCode varchar(10),
    primary key (supplierId, productCode),
    foreign key (productCode) references product (productCode)
);
#table of a multi value attribute 
create table supplierPhoneNum(
	supplierId int,
    supplierPhoneNum varchar(15),
    primary key (supplierId, supplierPhoneNum),
    foreign key (supplierId) references supplier (supplierId)
);
create table purchase (
	purchaseNum int,
    productCode varchar (10),
    dateOfPurchase date,
    customerId int,
    purcahseAmount int,
    primary key (purchaseNum, productCode),
    foreign key (productCode) references product (productCode),
    foreign key (customerId) references customer (customerId)
);
create table mailBox (
	mailNum int,
    message varchar(100),
    username varchar(50),
    sendDate date,
    receiveDate date,
    primary key (mailNum, username),
    foreign key (username) references account (username)
);
create table emp_dep(
	employeeId int,
    depNum int,
    primary key (employeeId, depNum),
    foreign key (employeeId) references employee (employeeId),
    foreign key (depNum) references department (depNum)
);
create table emp_sale(
	employeeId int,
    saleId int,
    primary key (employeeId, saleId),
    foreign key (employeeId) references employee (employeeId),
    foreign key (saleId) references sale (saleId)
);
create table product_purchase (
	purchaseNum int,
	productCode varchar(10),
    primary key (purchaseNum, productCode),
    foreign key (purchaseNum) references purchase (purchaseNum),
    foreign key (productCode) references product (productCode) 
);
create table product_supplier (
	supplierId int,
	productCode varchar(10),
    primary key (supplierId, productCode),
    foreign key (supplierId) references supplier (supplierId),
    foreign key (productCode) references product (productCode) 
);
create table account_product(
	username varchar(50),
    productCode varchar(10),
    amountInCart int,
    primary key (username, productCode),
    foreign key (username) references account (username),
    foreign key (productCode) references product (productCode)
);

######################################################## DONT RUN ALL ############################################################
ALTER TABLE employee
ADD CONSTRAINT UQ_employee_empName UNIQUE(empName);

ALTER TABLE department
ADD depName varchar(50);


insert into department values(1, 1, 'vegetabels');
insert into department values(2, 1, 'meats');

insert into employee values(1, 'admin', 3000, 10);
insert into department values(1, 1, 'meats');
insert into product values(2, '2026-06-15',50,80, 'goat',1, 1, '1Raw-goat-shanks-meat-on-a-marble-board.jpg' );
########################################
insert into customer (customerId, customerName, customerAddress, customerGender, customerDateOfBirth) 
values (2, 'huda', 'nablus', 'female', '2000-01-22');
ALTER TABLE product
MODIFY imgsrc varchar(200);
SELECT productCode, proName, proAmount
FROM product
WHERE expDate = '2023-07-06';

alter table payment
add payDate date;

SELECT c.customerName, c.customerAddress, c.customerGender, c.customerDateOfBirth, a.password
FROM customer AS c
JOIN account AS a ON c.customerId = a.customerId
WHERE c.customerId = 4;

INSERT INTO purchase values(3,'c#11111', '2023-06-22', 4, 1);
INSERT INTO customer VALUES (3, 'fadi', 'ramallah', 'male','****','1999-11-04');
INSERT INTO product VALUES ('c#11111', '2023-12-01', 10, 'meat', 15, 'chicken', 2, 1,'Tefal-How-To-Clean-Chicken-The-Right-Way.jpg');
delete from purchase;
Delete from product; 

SELECT purchase.purchaseNum, purchase.purcahseAmount, product.proName, purchase.dateOfPurchase
FROM purchase 
JOIN product ON purchase.productCode = product.productCode
WHERE purchase.dateOfPurchase between '2023-07-01' and '2023-07-30';

SELECT payId, amount, currency, payDate
FROM payment
WHERE customerId = 1 and payDate BETWEEN '2023-07-01' and '2023-07-04' ;

INSERT INTO department VALUES(7,1,'nutrition');

SELECT purchase.purchaseNum, purchase.purcahseAmount, customer.customerName
FROM purchase
JOIN customer ON purchase.customerId = customer.customerId
WHERE purchase.dateOfPurchase BETWEEN '2023-07-01' AND '2023-07-08'
AND purchase.customerId = '2';

insert into account (username, password, employeeId,dateOfCreation)
values  ('admin', 'admin', 1, '2023-06-04');
insert into empphonenum values(1, 0599647713);
select * from employee;
select * from customer;
select * from account;
select * from customerphonenum;
select * from empphonenum;
delete  from account;
delete from customerphonenum;
delete from customer;
delete from empphonenum;
delete from employee;
alter table product
add  imgsrc varchar(150);
select * from product
update product
set imgsrc='Raw-goat-shanks-meat-on-a-marble-board.jpg'
where productCode = 2;
SET SQL_SAFE_UPDATES = 0;
Alter table product
Add unique (proName);
delete from product;
SELECT COUNT(employeeId) FROM employee;
ALTER TABLE mailBox 
drop column receiveDate;
ALTER TABLE product
ADD imgsrc varchar(50);
