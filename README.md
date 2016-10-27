# BankATMDummy
This is only A dummy ATM project in JDBC Practise
1. Create a database in oracle 
DB anme XE
usename system
password oracle


2.create Table


CREATE TABLE bankatm(
   password varchar(20),
   accountNum int primary key,
   addr varchar(50) not null,
   money number(10,2) not null deafault 0,
   name varchar(20) not null
)

3. run the project
