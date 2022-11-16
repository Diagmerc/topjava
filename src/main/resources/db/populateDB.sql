delete
from user_roles;
delete
from users;
delete
from meals;
alter SEQUENCE global_seq RESTART with 100000;

insert into users (name, email, password)
values ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin'),
       ('Guest', 'guest@gmail.com', 'guest');

insert into user_roles (role, user_id)
values ('USER', 100000),
       ('ADMIN', 100001);

insert into meals (user_id, date_time, description, calories)
values (100000, '2022-01-22 21:00', 'ужин', 200),
       (100000, '2022-01-22 15:00', 'обед', 300),
       (100001, '2022-01-22 10:00', 'завтрак', 500),
        (100000,'2022-01-25 15:00', 'обед25', 300)
;


