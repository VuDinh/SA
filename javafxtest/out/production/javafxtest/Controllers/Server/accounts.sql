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
create table monsterPositions(
    id integer primary key,
    row integer,
    col integer
)
insert into monsterPositions(id,row,col) values(0,13,9);
insert into monsterPositions(id,row,col) values(0,9,15);
insert into monsterPositions(id,row,col) values(0,19,15);
insert into monsterPositions(id,row,col) values(0,26,9);
insert into monsterPositions(id,row,col) values(0,25,20);
insert into monsterPositions(id,row,col) values(0,30,15);
insert into monsterPositions(id,row,col) values(0,30,21);
insert into monsterPositions(id,row,col) values(0,26,32);
insert into monsterPositions(id,row,col) values(0,20,26);
insert into monsterPositions(id,row,col) values(0,14,20);
insert into monsterPositions(id,row,col) values(0,9,26);
insert into monsterPositions(id,row,col) values(0,14,32);


