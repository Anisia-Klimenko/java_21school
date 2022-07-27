create schema if not exists repo;

drop table if exists repo.user;

create table repo.user (
    id          serial primary key ,
    email       text
);

insert into repo.user (email) values ('mike@mail.com');
insert into repo.user (email) values ('john@mail.com');
insert into repo.user (email) values ('sam@mail.com');
insert into repo.user (email) values ('karen@mail.com');
insert into repo.user (email) values ('luke@mail.com');
