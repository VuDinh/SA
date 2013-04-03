drop table if exists accounts;
create table accounts(
  username text primary key,
  message text
);
insert into accounts(username,message) values('long','123');
insert into accounts(username,message) values('dinh','123');
insert into accounts(username,message) values('hung','123');
insert into accounts(username,message) values('quan','123');
