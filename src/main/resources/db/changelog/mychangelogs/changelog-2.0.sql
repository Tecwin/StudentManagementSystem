--liquibase formatted sql
        
--changeset root:7

create table roles(
id Integer not null auto_increment,
role varchar(20),
constraint pk_id primary key(id));

create table student (
id Integer not null auto_increment,
name varchar(20),
user_name varchar(20),
password varchar(500),
address varchar(50),
phone_no varchar(10) ,
CONSTRAINT chk_phone CHECK (phone_no not like '%[^0-9]%'),
role_id integer default null,
constraint pk primary key(id),
constraint FK foreign key(role_id) references roles(id));



create table management(
id Integer not null auto_increment,
user_name varchar(20),
password varchar(500),
role_id Integer default null,
constraint pk_management primary key(id),
constraint fk_management foreign key(role_id) references roles(id));



create table professor(
id Integer not null auto_increment,
name varchar(20),
user_name varchar(20),
password varchar(500),
address varchar(50),
phone_no varchar(10) ,
CONSTRAINT chk_phone_professor CHECK (phone_no not like '%[^0-9]%'),
role_id integer default null,
constraint pk_professor primary key(id),
constraint FK_professor foreign key(role_id) references roles(id));

create table course(
id integer not null  auto_increment,
title varchar(50),
professor_id integer default null,
constraint pk_course primary key(id),
constraint fk_course foreign key(professor_id) references professor(id)
);
create table reviews(
id integer not null auto_increment,
comment varchar(300),
course_id integer default null,
constraint pk_reviews primary key(id),
constraint fk_course_reviews foreign key(course_id) references course(id));


create table course_student(
course_id integer not null,
student_id integer not null,
primary key(course_id,student_id),
constraint fk_course_01 foreign key(course_id) references course(id),
constraint fk_student_01 foreign key(student_id) references student(id));



insert into roles (role) values ('ROLE_ADMIN');
insert into roles (role) values ('ROLE_Student');
insert into roles (role) values ('ROLE_Professor');
insert into management(user_name,password,role_id) values('admin','$2a$10$W19yz6KKDSYivDveUmJYVuBRBDrK.z2QwyM3z9tpM2ZzikWCVwPka',1);
