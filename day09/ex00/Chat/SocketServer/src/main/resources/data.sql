create schema if not exists repo;

drop table if exists repo.userTable;

create table repo.userTable (
   id          serial primary key ,
   userName       text ,
   password    text
);
