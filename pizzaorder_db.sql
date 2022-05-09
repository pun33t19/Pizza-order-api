drop database pizzaorderdb;
drop user pizzaorder;
create user pizzaorder with password 'password';
create database pizzaorderdb with template=template0 owner=pizzaorder;
\connect pizzaorderdb;
alter default privileges grant all on tables to pizzaorder;
alter default privileges grant all on sequences to pizzaorder;

create table p_users(
user_id integer primary key,
first_name varchar(20) not null,
last_name varchar(20) not null,
email varchar(30) not null,
password text not null
);

create table p_crust(
c_id integer primary key,
name text,
default_size integer
);

create table p_item(
p_id integer primary key,
name text,
is_veg integer,
description text,
default_crust integer
);


create table p_size(
s_id integer primary key,
name varchar(15),
price integer,
crust_id integer not null,
pizza_id integer not null
);

alter table p_size add constraint p_constraint_fk foreign key(pizza_id) references p_item(p_id);
alter table p_size add constraint c_constraint_fk foreign key(crust_id) references p_crust(c_id);







create table user_cart_items(
cart_id integer primary key,
pizza_item integer not null,
crust_id integer not null,
size_id integer not null,
user_id integer not null
);

alter table user_cart_items add constraint crust_constraint_fk foreign key (crust_id) references p_crust(c_id);
alter table user_cart_items add constraint user_constraint_fk foreign key (user_id) references p_users(user_id);
alter table user_cart_items add constraint pizza_constraint_fk foreign key (pizza_item) references p_item(p_id);
alter table user_cart_items add constraint size_constraint_fk foreign key (size_id) references p_size(s_id);




create sequence p_users_seq increment 1 start 1;
create sequence p_item_seq increment 1 start 1;
create sequence p_crust_seq increment 1 start 1;
create sequence p_size_seq increment 1 start 1;
create sequence p_cart_seq increment 1 start 1;