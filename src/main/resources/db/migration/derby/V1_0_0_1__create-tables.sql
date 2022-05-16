create table city (
    id            bigint,
    name          varchar(255),
    primary key   (id)
);

create table "USER" (
    id            bigint,
    login         varchar(255),
    password      varchar(255),
    points        decimal(20, 2),
    contact       varchar(255),
    primary key   (id)
);

create table fridge (
    id            bigint,
    user_id       bigint,
    primary key   (id),
    foreign key   (user_id) references "USER"
);

create table product (
    id            bigint,
    name          varchar(255),
    expiration_date date,
    primary key   (id)
);

create table fridge_item (
    id            bigint,
    quantity      bigint,
    product_id    bigint,
    fridge_id     bigint,
    primary key   (id),
    foreign key (fridge_id) references fridge,
    foreign key (product_id) references product
);

create table offer (
    id           bigint,
    price        decimal(20, 2),
    date         date,
    city_id      bigint,
    description  varchar(4000),
    availability varchar(255),
    user_id      bigint,
    product_id   bigint,
    primary key (id),
    foreign key (user_id) references "USER",
    foreign key (product_id) references product
);

create table "TRANSACTION" (
    id          bigint,
    seller_id   bigint,
    buyer_id    bigint,
    date        date,
    foreign key (seller_id) references "USER",
    foreign key (buyer_id) references "USER"
);

CREATE SEQUENCE city_id_seq start with 1;
CREATE SEQUENCE fridge_id_seq start with 1;
CREATE SEQUENCE fridge_item_id_seq start with 1;
CREATE SEQUENCE offers_id_seq start with 1;
CREATE SEQUENCE products_id_seq start with 1;
CREATE SEQUENCE transaction_id_seq start with 1;
CREATE SEQUENCE users_id_seq start with 1;

