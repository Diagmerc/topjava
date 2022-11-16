drop TABLE IF EXISTS user_roles;
drop TABLE IF EXISTS meals;
drop TABLE IF EXISTS users;
drop SEQUENCE IF EXISTS global_seq;

create sequence global_seq start with 100000;

create table users
(
    id               integer primary key default nextval('global_seq'),
    name             VARCHAR                           NOT NULL,
    email            varchar                           not null,
    password         varchar                           not null,
    registered       timestamp           default now() not null,
    enabled          bool                default true  not null,
    calories_per_day integer             default 2000  not null
);
create unique index users_unique_email_idx on users (email);

create table user_roles
(
    user_id integer not null,
    role    varchar not null,
    constraint user_roles_idx unique (user_id, role),
    foreign key (user_id) references users (id) on delete cascade
);

create table meals
(
    id               integer primary key default nextval('global_seq'),
    user_id         integer                             ,
    date_time       timestamp             default now() not null,
    description     varchar                             not null,
    calories        integer                             not null,
    constraint meals_user_datetime_idx unique (user_id, date_time),
    foreign key (user_id) references users (id) on delete cascade
);
