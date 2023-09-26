--liquibase formatted sql
        
--changeset nvoxland:1
create table person2 (  
    id int primary key,
    name varchar(255)  
);