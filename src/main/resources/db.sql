select * from users;
insert into users(username, password, age,email)
values ('user','user',20,'user@gmail.com'),
       ('admin','admin',30,'admin@gmail.com');


select * from roles;
insert into roles (role)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

select * from user_roles;
insert into user_roles(user_id,role_id)
values (1,1),
       (2,1),
       (2,2);