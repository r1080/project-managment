use project;

CREATE TABLE USER 
(
ID INT NOT NULL AUTO_INCREMENT,
FIRST_NAME VARCHAR(20),
LAST_NAME VARCHAR(20),
EMAIL VARCHAR(20),
PASSWORD VARCHAR(256), 
PRIMARY KEY (ID),
UNIQUE KEY (EMAIL)
);

CREATE TABLE ROLE 
(
ID INT NOT NULL AUTO_INCREMENT,
NAME VARCHAR(20),
PRIMARY KEY (ID)
);

CREATE TABLE USER_ROLE(
USER_ID int,
ROLE_ID int,
FOREIGN KEY (user_id)
REFERENCES user(id),
FOREIGN KEY (role_id)
REFERENCES role(id)
);

insert into user(first_name,last_name,email,password) values ('Raghav','Tenneti','raghavtenneti09@gmail.com','');
insert into user(first_name,last_name,email,password) values ('Rikki','Personal','raghavtenneti11@gmail.com','');

insert into role values(1,'ROLE_ADMIN');
insert into role values(2,'ROLE_USER');

insert into user_role values(1,1);
insert into user_role values(2,2);

select * from user;
select * from role;
select * from user_role;
