
-- Skript for å opprette databasen og legge inn litt data
    -- Skjema = Ansatt
        -- Tabell(er) = Ansatt,Avdeling

-- MERK!!! DROP SCHEMA ... CASCADE sletter alt !!!
DROP SCHEMA IF EXISTS oblig3 CASCADE;

CREATE SCHEMA oblig3;
SET search_path TO oblig3;
    
-- Ikke nødvendig å slette tabellen(e) siden vi har tomt skjema, men ...
-- DROP TABLE IF EXISTS person CASCADE;

CREATE TABLE Avdeling 
(
  avdeling_id serial PRIMARY KEY,
  avdeling_navn varchar (25),
  avdeling_sjef integer NOT NULL
);



CREATE TABLE Ansatt
(
    ansatt_id serial PRIMARY KEY,
    ansatt_bn varchar (3) UNIQUE,
    fornavn varchar (25),
    etternavn varchar (25),
    ansattelse_dato DATE,
    stilling varchar (25),
    maanedslonn integer,
    avdeling_id int NOT NULL,
	FOREIGN KEY (avdeling_id) REFERENCES Avdeling(avdeling_id)
	
);


CREATE TABLE Prosjekt
(
  prosjekt_id serial PRIMARY KEY,
  prosjekt_navn varchar (25),
  prosjekt_beskrivelse text
);

CREATE TABLE ProsjektDeltagelse
(
 ansattid integer,
 prosjektid integer,
 rolle varchar(25),
 timer integer,
 CONSTRAINT deltakelsePK PRIMARY KEY(ansattid,prosjektid),
 FOREIGN KEY(ansattid) REFERENCES Ansatt(ansatt_id),
 FOREIGN KEY(prosjektid) REFERENCES Prosjekt(prosjekt_id)
);



INSERT INTO Avdeling (avdeling_navn, avdeling_sjef)
VALUES ('IT', 1);

INSERT INTO Ansatt (ansatt_bn, fornavn, etternavn, ansattelse_dato, stilling, maanedslonn, avdeling_id)
VALUES ('lph', 'Linda', 'Petersen', '2021-01-01', 'IT-konsulent', 50000, 1);




ALTER TABLE Avdeling
ADD CONSTRAINT avdelingFK
FOREIGN KEY (avdeling_sjef)
REFERENCES Ansatt(ansatt_id);


INSERT INTO Ansatt (ansatt_bn, fornavn, etternavn, ansattelse_dato, stilling, maanedslonn, avdeling_id)
VALUES 
       ('kth', 'Kari', 'Thomsen', '2018-08-01', 'IT-tekniker', 40000, 1),
       ('ssh', 'Sara', 'Hansen', '2015-02-15', 'IT-konsulent', 50000, 1),
       ('lle', 'Lars', 'Eriksen', '2012-03-10', 'IT-tekniker', 40000, 1);


INSERT INTO Avdeling (avdeling_navn, avdeling_sjef) VALUES 
       ('HR', 2),
       ('Salg', 3);
       
UPDATE oblig3.Ansatt SET avdeling_id = 2 WHERE ansatt_id = 2;
UPDATE oblig3.Ansatt SET avdeling_id = 3 WHERE ansatt_id = 3;




INSERT INTO Ansatt (ansatt_bn, fornavn, etternavn, ansattelse_dato, stilling, maanedslonn, avdeling_id)
VALUES 
       ('jkd', 'John', 'Doe', '2020-05-15', 'HR-konsulent', 45000, 2),
       ('mso', 'Mark', 'Solberg', '2019-11-10', 'Salgssjef', 60000, 3),
       ('ahj', 'Anna', 'Jensen', '2017-04-20', 'HR-assistent', 35000, 2),
       ('tbe', 'Tom', 'Berg', '2016-10-05', 'Salgsrepresentant', 30000, 3),
       ('mle', 'Morten', 'Larsen', '2014-09-30', 'HR-konsulent', 45000, 2),
       ('dso', 'David', 'Svendsen', '2013-06-20', 'Salgsrepresentant', 30000, 3);
     

INSERT INTO Prosjekt (prosjekt_navn, prosjekt_beskrivelse) 
VALUES
  ('Prosjekt1','Beskrivelse av prosjekt1'),
  ('Prosjekt2','Beskrivelse av prosjekt2');


INSERT INTO ProsjektDeltagelse(ansattid,prosjektid,rolle,timer)
VALUES
  (2,1,'Observant',20),
  (9,2,'Calculator',30);

