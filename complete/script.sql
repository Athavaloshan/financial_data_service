-- create database financial_data_service;
-- use financial_data_service;
create table users
(
    username varchar(10) not null, 
    Password varchar(10) not null, 
    first_name varchar(10) not null, 
    last_name varchar(10) not null, 
    birthday Date not null, 
    email varchar(20), 
    profile_picture_link varchar(20)
);