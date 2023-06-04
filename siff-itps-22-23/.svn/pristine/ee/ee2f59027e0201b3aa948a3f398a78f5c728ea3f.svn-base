create database MYSINFORMATION_DB;
use MYSINFORMATION_DB;
-- drop database MYSINFORMATION_DB
create table utente (
username varchar(50) primary key,
email varchar(50),
pw varchar(50),
ruolo int(1)
);
create table fonte (
id_fonte int auto_increment primary key,
nome varchar(50),
url varchar(100),
indice float
);

create table segnalazione (
id_segnalazione int auto_increment primary key,
titolo varchar(50),
descrizione varchar(200),
mittente varchar(50),
fonte_segnalata int,
foreign key (mittente) REFERENCES utente(username),
foreign key (fonte_segnalata) references fonte(id_fonte)
);

insert into utente values ("Fabian","p@p","java",1);
insert into utente values ("utente","p@p2","java",0);

insert into fonte(nome,url) values("larepubblica","ww");
insert into fonte(nome,url) values("bufale","bufale.net");
select * from utente;