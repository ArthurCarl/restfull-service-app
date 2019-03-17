insert into user ( id, username, password) values ( 1, 'alfred','1234');
insert into ROLE ( id, name) values ( 1, 'ROLE_USER');
insert into user_roles(user_id,roles_id) values (1,1);