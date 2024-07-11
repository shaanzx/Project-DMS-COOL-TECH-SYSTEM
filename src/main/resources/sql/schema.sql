drop database dmscooltech;

create database dmscooltech;

use dmscooltech;

create table user(
                     uId varchar(5) primary key,
                     uName varchar(50) NOT NULL,
                     uPassword varchar(10) NOT NULL
);
INSERT INTO user(uId,uName,uPassword)VALUES
                                         ('u001','shan','1234'),
                                         ('u002','lasha','1234');

create table customer(
                         cId varchar(5) primary key,
                         name varchar(50)NOT NULL,
                         address varchar(50)NOT NULL,
                         tel varchar(12)NOT NULL,
                         email varchar(50)NOT NULL,
                         uId varchar(5),
                         foreign key(uId) references user(uId) on update cascade on delete cascade
);
INSERT INTO customer(cId,name,address,tel,email,uId)VALUES
                                                  ('C001','lakshan','galle','0776986455','shan@@gmail.com','u001'),
                                                  ('C002','sisiwari','mathara','0776986456','lasha@@gmail.com','u002');


create table report(
                       rId varchar(5) primary key,
                       category varchar(25)
);
INSERT INTO report(rId,category)VALUES
                                    ('R001','Payment report'),
                                    ('R002','Order report');

create table daignosisReport(
                                date date,
                                details varchar(100),
                                rId varchar(5),
                                foreign key(rId) references report(rId) on update cascade on delete cascade
);
INSERT INTO daignosisReport(date,details,rId)VALUES
                                                     ('20240401','Condenser','R001'),
                                                     ('20240402','Cooler','R001');

create table orders(
                       oId varchar(5)primary key,
                       cId varchar(5),
                       foreign key(cId) references customer(cId) on update cascade on delete cascade,
                       date Date
);
INSERT INTO orders(oId,cId,date)VALUES
                                   ('OR001','C001',20240321),
                                   ('OR002','C002',20240322);

create table item(
                     iCode varchar(5) primary key,
                     iName varchar(50)NOT NULL,
                     iCategory varchar(30)NOT NULL,
                     qtyOnHand int(5),
                     iPrice decimal(10,2)NOT NULL,
                     date Date
);
INSERT INTO item(iCode,iName,iCategory,qtyOnHand,iPrice,date)VALUES
                                                                           ('I001','Condenser','TOYOTA',20,7000,20240321),
                                                                           ('I002','Cooler','NISSAN',25,15000,20240321);

create table orderDetails(
                             oId varchar(5),
                             foreign key(oId) references orders(oId) on update cascade on delete cascade,
                             iCode varchar(5),
                             foreign key(iCode) references item(iCode) on update cascade on delete cascade,
                             oDate date NOT NULL,
                             qty int(5),
                            unitPrice decimal(10,2),
                             oPrice decimal(10,2) NOT NULL
);
INSERT INTO orderDetails(oId,iCode,oDate,qty,unitPrice,oPrice)VALUES
                                                        ('OR001','I001',20240321,5,7000,35000),
                                                        ('OR002','I002',20240321,5,15000,75000);

create table employee(
                         eId varchar(5) primary key,
                         eName varchar(50)NOT NULL,
                         eAddress varchar(100),
                         eTel varchar(12)NOT NULL,
                         ejobRole varchar(20),
                         uId varchar(5),
                         foreign key(uId) references user(uId) on update cascade on delete cascade
);
INSERT INTO employee(eId,eName,eAddress,eTel,ejobRole,uId)VALUES
                                                              ('E001','Kavindi','Galle','0775588999','Cashier','u001'),
                                                              ('E002','Dinuka','Galle','0770371593','Techician','u002');

create table salary(
                       sId varchar(5) primary key,
                       sAmount decimal(10,2),
                       date date,
                       eId varchar(5),
                       foreign key(eId) references employee(eId) on update cascade on delete cascade
);
INSERT INTO salary(sId,sAmount,date,eid)VALUES
                                            ('S001',45000,20240325,'E001'),
                                            ('S002',65000,20240325,'E002');

create table vehicle(
                        vNo varchar(15) primary key,
                        vModel varchar(50),
                        vType varchar(50),
                        cId varchar(5),
                        foreign key(cId) references customer(cId) on update cascade on delete cascade
);
INSERT INTO vehicle(vNo,vModel,vType,cId)VALUES
                                             ('KP-1111','TOYOTA','PRADO','C001'),
                                             ('PB-2048','NISSAN','VAN','C002');

create table repair(
                       rId varchar(15) primary key,
                       vNo varchar(15),
                       foreign key(vNo) references vehicle(vNo) on update cascade on delete cascade,
                       description varchar(100),
                       repairDate date,
                       repairCost decimal(10,2),
                       eId varchar(5),
                       foreign key(eId) references employee(eId) on update cascade on delete cascade,
/*                     pId varchar(5),
                       foreign key(pId) references payment(pId) on update cascade on delete cascade,*/
                       iCode varchar(5),
                       foreign key(iCode) references item(iCode) on update cascade on delete cascade,
                       totalPrice decimal(10,2)
);
INSERT INTO repair(rId,vNo,description,repairDate,repairCost,eId,iCode,totalPrice)VALUES
                    ('R001','KP-1111','Repair Cooler','20230421',5000,'E001','I002',20000),
                    ('R002','PB-2048','Repair Condenser','20230421',4500,'E002','I001',11500);

create table payment(
                        pId varchar(5) primary key,
                        cId varchar(5)not null,
                        foreign key(cId) references customer(cId) on update cascade on delete cascade,
                        oId varchar(5),
                        foreign key(oId) references orders(oId) on update cascade on delete cascade,
                        rId varchar(15),
                        foreign key(rId) references repair(rId) on update cascade on delete cascade,
                        totalAmount decimal(10,2)NOT NULL,
                        date Date,
                        customerPayment decimal(10,2),
                        customerBalance decimal(10,2)

);
INSERT INTO payment(pId,cId,oId,rId,totalAmount,date,customerPayment,customerBalance)VALUES
                                                        ('P001','C001','OR001','R001',7000,20240321,7500,500),
                                                        ('P002','C002','OR002','R002',15000,20240321,15500,500);

/*INSERT INTO repair(repairDate,description,repairCost,eId,vNo,iCode)VALUES
                                                                ('20240401','REPLACE Condenser',5000,'E002','KP-1111','I001'),
                                                                ('20240402','REPLACE Cooler',2500,'E002','PB-2048','I002');*/

create table supplier(
                         supId varchar(5) primary key,
                         supName varchar(50)NOT NULL,
                         supItem varchar(50),
                         uId varchar(5),
                         foreign key(uId) references user(uId) on update cascade on delete cascade
);
INSERT INTO supplier(supId,supName,supItem,uId)VALUES
                                                   ('S001','MOHOMAD','Condensor','u001'),
                                                   ('S002','JAGATH','Cooler','u001');

create table supplierDetails(
                                itemName varchar(50),
                                itemPrice decimal(10,2),
                                itemQty int(10),
                                innovice varchar(100),
                                supplyDate date,
                                supId varchar(5),
                                foreign key(supId) references supplier(supId) on update cascade on delete cascade,
                                iCode varchar(5),
                                foreign key(iCode) references item(iCode) on update cascade on delete cascade
);
INSERT INTO supplierDetails(itemName,itemPrice,itemQty,innovice,supplyDate,supId,iCode)VALUES
                                                                                           ('condensor',5000,10,'IN001',20240201,'S001','I001'),
                                                                                           ('cooler',10000,5,'IN002',20240201,'S002','I002')

/*select item.iName,customer.name,iPrice,orderdetails.qty,(item.iPrice*orderdetails.qty) AS value,
       customerPayment,customerBalance
FROM orderdetails
         INNER JOIN orders
                    ON orderdetails.oId=orders.oId
         INNER JOIN item
                    ON item.iCode=orderdetails.iCode
         INNER JOIN payment
                    ON payment.oId=orders.oId
         INNER JOIN customer
                    ON customer.cId=orders.cId
WHERE orders.oId=(select max(orders.oId ) FROM orders)
ORDER BY orderdetails.oId desc;*/
