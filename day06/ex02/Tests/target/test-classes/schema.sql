drop table if exists product;

create table product (
    id      bigint primary key identity,
    name    varchar(50) not null ,
    price   decimal(10,2)
);