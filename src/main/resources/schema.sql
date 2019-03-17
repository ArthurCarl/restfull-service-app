DROP TABLE IF EXISTS user;
CREATE TABLE user(ID INT PRIMARY KEY, username VARCHAR(32) unique ,
password varchar(128),
accountNonExpired boolean default true ,
accountNonLocked boolean default true ,
credentialsNonExpired boolean default true ,
enabled boolean default true
);


DROP TABLE IF EXISTS role;
CREATE TABLE role(ID INT PRIMARY KEY,
name varchar(128)
);



drop table if exists user_roles;
create table user_roles(user_id INT not null,roles_id int not null);