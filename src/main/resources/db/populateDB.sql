DELETE
FROM user_roles;
DELETE
FROM users;
DELETE
FROM meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin'),
       ('Guest', 'guest@gmail.com', 'guest');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meals (user_id, date_time, description, calories)
VALUES
        (100001, '2022-01-22 10:00', 'Админ завтрак', 500),
        (100001, '2015-06-1 13:00', 'Админ ланч', 510),
        (100001, '2015-06-1 21:00', 'Админ ланч', 1500),
        (100000, '2022-01-22 21:00', 'ужин', 200),
        (100000, '2022-01-22 15:00', 'обед', 300),
        (100000,'2022-01-25 15:00', 'обед25', 300),
        (100000, '2020-01-31 00:00', 'Еда на граничное значение', 2001)
;



