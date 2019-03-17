DROP TABLE IF EXISTS user;
CREATE TABLE user(ID INT PRIMARY KEY, username VARCHAR(32) unique ,
password varchar(128),
account_Non_Expired boolean default true ,
account_Non_Locked boolean default true ,
credentials_Non_Expired boolean default true ,
enabled boolean default true
);


DROP TABLE IF EXISTS role;
CREATE TABLE role(ID INT PRIMARY KEY,
name varchar(128)
);



drop table if exists user_roles;
create table user_roles(user_id INT not null,roles_id int not null);