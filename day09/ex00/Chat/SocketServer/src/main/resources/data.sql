create schema if not exists repo;

drop table if exists repo.userTable;

create table repo.userTable (
   id          serial primary key ,
   email       text ,
   password    text
);

insert into repo.userTable(email, password) values ('mike@mail.com', '12345mikeqwerty');
insert into repo.userTable(email, password) values ('john@mail.com', '12345johnqwerty');
insert into repo.userTable(email, password) values ('sam@mail.com', '12345samqwerty');
insert into repo.userTable(email, password) values ('karen@mail.com', '12345karenqwerty');
insert into repo.userTable(email, password) values ('luke@mail.com', '12345lukeqwerty');