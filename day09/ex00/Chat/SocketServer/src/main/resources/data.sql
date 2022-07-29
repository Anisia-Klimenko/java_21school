create schema if not exists repo;

drop table if exists repo.userTable;

create table repo.userTable (
   id          serial primary key ,
   userName       text ,
   password    text
);

insert into repo.userTable(userName, password) values ('mike@mail.com', '12345mikeqwerty');
insert into repo.userTable(userName, password) values ('john@mail.com', '12345johnqwerty');
insert into repo.userTable(userName, password) values ('sam@mail.com', '12345samqwerty');
insert into repo.userTable(userName, password) values ('karen@mail.com', '12345karenqwerty');
insert into repo.userTable(userName, password) values ('luke@mail.com', '12345lukeqwerty');