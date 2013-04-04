drop table if exists accounts;
create table accounts(
  username text primary key,
  password text
);
insert into accounts(username,password) values('long','123');
insert into accounts(username,password) values('dinh','123');
insert into accounts(username,password) values('hung','123');
insert into accounts(username,password) values('quan','123');
