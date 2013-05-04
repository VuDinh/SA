drop table if exists accounts;
drop table if exists heroBeginPositions;
drop table if exists monsterBeginPositions;
drop table if exists monsterPositions;
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
create table monsterPositions(
    id integer primary key,
    row integer,
    col integer
);
insert into monsterPositions(id,row,col) values(0,13,8);
insert into monsterPositions(id,row,col) values(1,9,14);
insert into monsterPositions(id,row,col) values(2,19,14);
insert into monsterPositions(id,row,col) values(3,26,8);
insert into monsterPositions(id,row,col) values(4,25,20);
insert into monsterPositions(id,row,col) values(5,30,14);
insert into monsterPositions(id,row,col) values(6,30,25);
insert into monsterPositions(id,row,col) values(7,26,31);
insert into monsterPositions(id,row,col) values(8,20,25);
insert into monsterPositions(id,row,col) values(9,14,19);
insert into monsterPositions(id,row,col) values(10,9,25);
insert into monsterPositions(id,row,col) values(11,14,31);


