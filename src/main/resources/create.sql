drop table car;
drop table ticket;
drop table parkinglot;
create sequence parkinglotSequence start 1;
CREATE TABLE car (
    registrationnumber VARCHAR(50) NOT NULL,
    colour VARCHAR(10)
)
logging;
ALTER TABLE car ADD CONSTRAINT car_pk PRIMARY KEY ( registrationnumber );
CREATE TABLE parkinglot (
    slotnumber INTEGER NOT NULL,
    isavailable CHAR(1)
)
logging;
ALTER TABLE parkinglot ADD CONSTRAINT parkinglot_pk PRIMARY KEY ( slotnumber );

CREATE TABLE ticket (
    ticketid INTEGER NOT NULL,
    registrationnumer VARCHAR(50) NOT NULL,
    slotnumer INTEGER NOT NULL
)
logging;
ALTER TABLE ticket ADD CONSTRAINT ticket_pk PRIMARY KEY ( ticketid );
logging;
ALTER TABLE ticket
    ADD CONSTRAINT ticket_car_fk FOREIGN KEY ( registrationnumer )
        REFERENCES car ( registrationnumber )
    NOT DEFERRABLE;
logging;
ALTER TABLE ticket
    ADD CONSTRAINT ticket_parkinglot_fk FOREIGN KEY ( slotnumer )
        REFERENCES parkinglot ( slotnumber )
    NOT DEFERRABLE;
