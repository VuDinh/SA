drop table if exists accounts;
drop table if exists heroBeginPositions;
drop table if exists monsterBeginPositions;
create table accounts(
  username text primary key,
  password text
);
insert into accounts(username,password) values('long','123');
insert into accounts(username,password) values('dinh','123');
insert into accounts(username,password) values('hung','123');
insert into accounts(username,password) values('quan','123');
create table heroBeginPositions(
    id integer primary key,
    row integer,
    col integer
);
insert into heroBeginPositions(id,row,col) values(0,37,1);
insert into heroBeginPositions(id,row,col) values(1,38,1);
insert into heroBeginPositions(id,row,col) values(2,39,1);
insert into heroBeginPositions(id,row,col) values(3,38,0);
insert into heroBeginPositions(id,row,col) values(4,38,2);
insert into heroBeginPositions(id,row,col) values(5,0,38);
insert into heroBeginPositions(id,row,col) values(6,1,38);
insert into heroBeginPositions(id,row,col) values(7,2,38);
insert into heroBeginPositions(id,row,col) values(8,1,37);
insert into heroBeginPositions(id,row,col) values(9,1,39);


