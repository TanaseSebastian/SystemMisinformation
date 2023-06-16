--  create database MYSINFORMATION_DB;
use MYSINFORMATION_DB;
-- drop database MYSINFORMATION_DB;
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
indice int default 100
);

create table segnalazione (
id_segnalazione int auto_increment primary key,
titolo varchar(50),
descrizione varchar(200),
mittente varchar(50),
fonte_segnalata int,
stato int,
foreign key (mittente) REFERENCES utente(username),
foreign key (fonte_segnalata) references fonte(id_fonte)
);


create table filtroFonti (
fonte int,
utente varchar(50),
foreign key (utente) REFERENCES utente(username),
foreign key (fonte) references fonte(id_fonte)
);

create table whiteList(
fonte int,
foreign key (fonte ) references fonte(id_fonte)
);

create table blackList(
fonte int,
foreign key (fonte) references fonte(id_fonte)
);


insert into utente values ("Seb","p@p","java",2);
insert into utente values ("Fabian","p@p","java",1);
insert into utente values ("utente","p@p2","java",0);
insert into utente values ("Sebastian","p@p2","java",0);

insert into fonte(nome,url) values("larepubblica","ww");
insert into fonte(nome,url) values("bufale","bufale.net");

-- UPDATE fonte set indice = indice + 1 where nome = "La Repubblica";



select *  from fonte;
drop table fonte;
select * from utente;
select * from segnalazione;



insert into fonte(nome,url) values("Corriere della Sera","www.corriere.it");
insert into fonte(nome,url) values("La Repubblica","www.repubblica.it");
insert into fonte(nome,url) values("La Stampa","www.lastampa.it");
insert into fonte(nome,url) values("Il Sole 24 Ore","www.ilsole24ore.com");
insert into fonte(nome,url) values("Il Messaggero","www.ilmessaggero.it");
insert into fonte(nome,url) values("Il Fatto Quotidiano","www.ilfattoquotidiano.it");
insert into fonte(nome,url) values("Il Giornale","www.ilgiornale.it");
insert into fonte(nome,url) values("La Gazzetta dello Sport","www.gazzetta.it");
insert into fonte(nome,url) values("ANSA","www.ansa.it");
insert into fonte(nome,url) values("Rai News","www.rainews.it");
insert into fonte(nome,url) values("Il Mattino","www.ilmattino.it");
insert into fonte(nome,url) values("Il Gazzettino","www.ilgazzettino.it");
insert into fonte(nome,url) values("La Nazione","www.lanazione.it");
insert into fonte(nome,url) values("Il Messaggero Veneto","www.ilmessaggeroveneto.it");
insert into fonte(nome,url) values("Il Secolo XIX","www.ilsecoloxix.it");
insert into fonte(nome,url) values("Il Resto del Carlino","www.ilrestodelcarlino.it");
insert into fonte(nome,url) values("La Sicilia","www.lasicilia.it");
insert into fonte(nome,url) values("Il Tirreno","www.iltirreno.it");
insert into fonte(nome,url) values("La Nuova Sardegna","www.lanuovasardegna.it");
insert into fonte(nome,url) values("Il Quotidiano Italiano","www.ilquotidianoitaliano.it");
insert into fonte(nome,url) values("Il Post","www.ilpost.it");
insert into fonte(nome,url) values("Huffington Post Italia","www.huffingtonpost.it");
insert into fonte(nome,url) values("Tgcom24","www.tgcom24.mediaset.it");
insert into fonte(nome,url) values("Fanpage","www.fanpage.it");
insert into fonte(nome,url) values("Today","www.today.it");
insert into fonte(nome,url) values("Il Giornale di Vicenza","www.ilgiornaledivicenza.it");
insert into fonte(nome,url) values("Il Giorno","www.ilgiorno.it");
insert into fonte(nome,url) values("Il Gazzettino di Venezia","www.ilgazzettinovesuviano.com");
insert into fonte(nome,url) values("Il Fatto Quotidiano","www.ilfattoquotidiano.it");
insert into fonte(nome,url) values("Il Denaro","www.ildenaro.it");
insert into fonte(nome,url) values("Il Centro","www.ilcentro.it");
insert into fonte(nome,url) values("Il Cittadino","www.ilcittadino.it");
insert into fonte(nome,url) values("Il Cittadino di Monza e Brianza","www.ilcittadino.it");
insert into fonte(nome,url) values("Il Cittadino di Lodi","www.ilcittadino.it");
insert into fonte(nome,url) values("Il Cittadino di Siena","www.ilcittadino.it");
insert into fonte(nome,url) values("Il Foglio","www.ilfoglio.it");
insert into fonte(nome,url) values("Il Fatto Alimentare","www.ilfattoalimentare.it");
insert into fonte(nome,url) values("Il Fatto Nisseno","www.ilfattosicilia.it");
insert into fonte(nome,url) values("Il Gazzettino del Chianti","www.ilgazzettinodichianti.it");
insert into fonte(nome,url) values("Il Gazzettino del Veneto","www.ilgazzettinodivenezia.it");
insert into fonte(nome,url) values("Il Gazzettino di Pordenone","www.ilgazzettino.it");
insert into fonte(nome,url) values("Il Gazzettino di Treviso","www.ilgazzettino.it");
insert into fonte(nome,url) values("Il Gazzettino di Belluno","www.ilgazzettino.it");
insert into fonte(nome,url) values("Il Gazzettino di Padova","www.ilgazzettino.it");
insert into fonte(nome,url) values("Il Gazzettino di Rovigo","www.ilgazzettino.it");
insert into fonte(nome,url) values("Il Giornale di Brescia","www.ilgiornaledibrescia.it");
insert into fonte(nome,url) values("Il Giornale di Calabria","www.ilgiornaledicalabria.it");
insert into fonte(nome,url) values("Il Giornale di Sicilia","www.ilgiornaledisicilia.it");
insert into fonte(nome,url) values("Il Gazzettino di Udine","www.ilgazzettino.it");
insert into fonte(nome,url) values("Il Gazzettino di Modena","www.ilgazzettino.it");
insert into fonte(nome,url) values("Il Gazzettino di Reggio Emilia","www.ilgazzettino.it");
insert into fonte(nome,url) values("Il Gazzettino di Ferrara","www.ilgazzettino.it");
insert into fonte(nome,url) values("Il Gazzettino di Mantova","www.ilgazzettino.it");
insert into fonte(nome,url) values("Il Gazzettino di Cremona","www.ilgazzettino.it");
insert into fonte(nome,url) values("Il Gazzettino di Piacenza","www.ilgazzettino.it");
insert into fonte(nome,url) values("Il Gazzettino di Parma","www.ilgazzettino.it");
insert into fonte(nome,url) values("Il Gazzettino di Rimini","www.ilgazzettino.it");
insert into fonte(nome,url) values("Il Gazzettino di Forlì-Cesena","www.ilgazzettino.it");
insert into fonte(nome,url) values("Il Gazzettino di Ravenna","www.ilgazzettino.it");
insert into fonte(nome,url) values("Il Gazzettino di Ferrara","www.ilgazzettino.it");
insert into fonte(nome,url) values("Il Gazzettino di Grosseto","www.ilgazzettinogrosseto.it");
insert into fonte(nome,url) values("Il Gazzettino di Livorno","www.ilgazzettinolivorno.it");
insert into fonte(nome,url) values("Il Gazzettino di Arezzo","www.ilgazzettinoarezzo.it");
insert into fonte(nome,url) values("Il Gazzettino di Sondrio","www.ilgazzettinosondrio.it");
insert into fonte(nome,url) values("Il Gazzettino di Varese","www.ilgazzettinovarese.it");
insert into fonte(nome,url) values("Il Gazzettino di Como","www.ilgazzettinocomo.it");
insert into fonte(nome,url) values("Il Gazzettino di Lecco","www.ilgazzettinolecco.it");
insert into fonte(nome,url) values("Il Gazzettino di Bergamo","www.ilgazzettinobergamo.it");
insert into fonte(nome,url) values("Il Gazzettino di Bologna","www.ilgazzettinobologna.it");
insert into fonte(nome,url) values("Il Gazzettino di Firenze","www.ilgazzettinofirenze.it");
insert into fonte(nome,url) values("Il Gazzettino di Pisa","www.ilgazzettinopisa.it");
insert into fonte(nome,url) values("Il Gazzettino di Siena","www.ilgazzettinosiena.it");
insert into fonte(nome,url) values("Il Gazzettino di Perugia","www.ilgazzettinoperugia.it");
insert into fonte(nome,url) values("Il Gazzettino di Terni","www.ilgazzettinoterni.it");
insert into fonte(nome,url) values("Il Gazzettino di Ancona","www.ilgazzettinoancona.it");
insert into fonte(nome,url) values("Il Gazzettino di Macerata","www.ilgazzettinomacerata.it");
insert into fonte(nome,url) values("Il Gazzettino di Pesaro","www.ilgazzettinopesaro.it");
insert into fonte(nome,url) values("Il Gazzettino di Ascoli Piceno","www.ilgazzettinoascoli.it");
insert into fonte(nome,url) values("Il Gazzettino di Ravenna","www.ilgazzettinoravenna.it");
insert into fonte(nome,url) values("Il Gazzettino di Ferrara","www.ilgazzettinoferrara.it");
insert into fonte(nome,url) values("Il Gazzettino di Vicenza","www.ilgazzettinovenezia.it");
insert into fonte(nome,url) values("Il Gazzettino di Verona","www.ilgazzettinoverona.it");
insert into fonte(nome,url) values("Il Gazzettino di Trento","www.ilgazzettinotrentino.it");
insert into fonte(nome,url) values("Il Gazzettino di Trieste","www.ilgazzettinotrieste.it");
insert into fonte(nome,url) values("Il Gazzettino di Bolzano","www.ilgazzettinobolzano.it");
insert into fonte(nome,url) values("Il Gazzettino di Foggia","www.ilgazzettinofoggia.it");
insert into fonte(nome,url) values("Il Gazzettino di Lecce","www.ilgazzettinolecce.it");
insert into fonte(nome,url) values("Il Gazzettino di Brindisi","www.ilgazzettinobrindisi.it");
insert into fonte(nome,url) values("Il Gazzettino di Taranto","www.ilgazzettinotaranto.it");
insert into fonte(nome,url) values("Il Gazzettino di Matera","www.ilgazzettinomatera.it");
insert into fonte(nome,url) values("Il Gazzettino di Potenza","www.ilgazzettinopotenza.it");
insert into fonte(nome,url) values("Il Gazzettino di Avellino","www.ilgazzettinoavellino.it");
insert into fonte(nome,url) values("Il Gazzettino di Salerno","www.ilgazzettinosalerno.it");
insert into fonte(nome,url) values("Il Gazzettino di Caserta","www.ilgazzettinocaserta.it");
insert into fonte(nome,url) values("Il Gazzettino di Napoli","www.ilgazzettinonapoli.it");
insert into fonte(nome,url) values("Il Gazzettino di Catania","www.ilgazzettinocatania.it");
insert into fonte(nome,url) values("Il Gazzettino di Palermo","www.ilgazzettinopalermo.it");
insert into fonte(nome,url) values("Il Gazzettino di Messina","www.ilgazzettinomessina.it");
insert into fonte(nome,url) values("Il Gazzettino di Cagliari","www.ilgazzettinocagliari.it");
insert into fonte(nome,url) values("Il Gazzettino di Sassari","www.ilgazzettinocagliari.it");
