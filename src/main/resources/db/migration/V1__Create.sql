create table shop.basket (
       id  bigserial not null,
        user_id int8,
        primary key (id)
    );
create table shop.basket_products (
       id  bigserial not null,
        count int4 not null,
        basket_id int8,
        product_id int8,
        primary key (id)
    );
create table shop.categories_products (
       product_id int8 not null,
        category_id int8 not null
    );
create table shop.category (
       id  bigserial not null,
        primary key (id)
    );
create table shop.orders (
       id  bigserial not null,
        date_created timestamp,
        price numeric(19, 2),
        status varchar(255),
        user_id int8,
        primary key (id)
    );
create table shop.orders_products (
       id  bigserial not null,
        count int4 not null,
        price int4 not null,
        order_id int8,
        product_id int8,
        primary key (id)
    );
create table shop.products (
       id  bigserial not null,
        description varchar(255),
        name varchar(255),
        price numeric(19, 2),
        primary key (id)
    );
create table shop.roles (
       id  bigserial not null,
        name varchar(255),
        primary key (id)
    );
create table shop.users (
       id  bigserial not null,
        email varchar(255),
        first_name varchar(255),
        img varchar(255),
        last_name varchar(255),
        password varchar(255),
        phone varchar(255),
        primary key (id)
    );
create table shop.users_roles (
       user_id int8 not null,
        role_id int8 not null
    );
alter table shop.users 
       add constraint email_constraint unique (email);
alter table shop.basket 
       add constraint user_id_fk 
       foreign key (user_id) 
       references shop.users;
alter table shop.basket_products 
       add constraint basket_id_fk 
       foreign key (basket_id) 
       references shop.basket;
alter table shop.basket_products 
       add constraint product_id_fk 
       foreign key (product_id) 
       references shop.products;
alter table shop.categories_products 
       add constraint category_id_fk 
       foreign key (category_id) 
       references shop.category;
alter table shop.categories_products 
       add constraint product_id_fk 
       foreign key (product_id) 
       references shop.products;
alter table shop.orders 
       add constraint user_id_fk 
       foreign key (user_id) 
       references shop.users;
alter table shop.orders_products 
       add constraint order_id_fk 
       foreign key (order_id) 
       references shop.orders;
alter table shop.orders_products 
       add constraint product_id_fk 
       foreign key (product_id) 
       references shop.products;
alter table shop.users_roles 
       add constraint role_id_fk 
       foreign key (role_id) 
       references shop.roles;
alter table shop.users_roles 
       add constraint user_id_fk 
       foreign key (user_id) 
       references shop.users;