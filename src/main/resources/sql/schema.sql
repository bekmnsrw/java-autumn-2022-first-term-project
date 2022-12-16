drop table if exists users cascade;
drop table if exists orders cascade;
drop table if exists product cascade;
drop table if exists cart cascade;
drop table if exists order_product cascade;

create table if not exists users(
    id bigserial primary key,
    is_admin bool default false,
    first_name varchar(50),
    second_name varchar(50),
    email varchar(50) unique,
    hashed_password varchar(32)
);

create table if not exists orders(
    id bigserial primary key,
    date varchar(50),
    price int,
    delivery_city varchar(50),
    delivery_street varchar(50),
    delivery_home varchar(50),
    delivery_flat varchar(50),
    user_id bigint references users(id)
);

create table if not exists product(
    id bigserial primary key,
    name varchar(50),
    description varchar(255),
    price int,
    category varchar(50)
);

create table if not exists cart(
    user_id bigint references users(id),
    product_id bigint references product(id),
    amount int default 1,
    price int
);

create table if not exists order_product(
    order_id bigint references orders(id),
    product_id bigint references product(id),
    amount int
);
