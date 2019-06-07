Please refer to the read me file before you start using this project.
## Software pre-requisites:
1. Docker
2. Kitematic
3. Java version 8
4. PostGres - try using the docker image for postgres at https://hub.docker.com/_/postgres
5. Maven
6. Unit testing
## Software Installation Instrtuctions
Step 1: Clone this git repository from https://github.com/shuklagauravjn/parkinglot.git
Step 2: Edit the properties file present at /src/main/resources/application.properties
Step 3: change the value for spring.datasource.url=jdbc:postgresql://<<your postgres host IP>>:<<your postgres host port>/<<your postgres database name>
Step 4: Go to the terminal window and the present working directory should be the root of this folder
Step 5: type mvn clean install This command will clean and later build this package. Check the logs and you should see some logs like this:
  Hibernate: alter table ticket drop constraint FK2ii5cq5xek9b6scbnn5h7nwia
  Hibernate: alter table ticket drop constraint FK5s6o4c33uj044cbfqxukrf9ki
  Hibernate: drop table if exists car cascade
  Hibernate: drop table if exists parkinglot cascade
  Hibernate: drop table if exists ticket cascade
  Hibernate: drop sequence if exists parkinglotsequence
  Hibernate: create sequence parkinglotsequence start 1 increment 50
  Hibernate: create table car (registrationnumber varchar(255) not null, colour varchar(255), primary key (registrationnumber))
  Hibernate: create table parkinglot (slotnumber int4 not null, isavailable varchar(255), primary key (slotnumber))
  Hibernate: create table ticket (ticketid int4 not null, registrationnumer varchar(255), slotnumer int4, primary key (ticketid))
  Hibernate: alter table ticket add constraint FK2ii5cq5xek9b6scbnn5h7nwia foreign key (registrationnumer) references car
  Hibernate: alter table ticket add constraint FK5s6o4c33uj044cbfqxukrf9ki foreign key (slotnumer) references parkinglot
Step 6: This means that your data base is setup.
Step 7: Check the logs and you will see "Tests run: 5, Failures: 0, Errors: 0, Skipped: 0" This means that the junit test has passed.
Step 7: A "BUILD SUCCESS" message means that things are developed properly on your local machine. In case you have any issue, please reach out to me at shuklagauravjn@gmail.com
Step 8: Now on the same folder in your terminal type java -jar target/parkingLot-REST-0.1.jar This will start the Springboot based parking lot application.
Step 9: 
## Technical Architecture
In this section we are detailing out the technical architecture of the parking lot application.
### Data Model
The data model of the application is as below. There are 2 primary entities. The first one is the car, which has the registraion number and the colour. In this table registration number is the primary key. The colour identifies the car. The second entitty is the ParkingLot. In this entity slot Number is the primary key and the isAvailable identifies if the slot is available or gone. The core entity of the application is the Ticket, which has ticketId as it's primary key. It has the registrtation number of the car and slot number which maps which car is allocated to which slot number in the real world and as primary key and foreign key mapping in the data base world.
![DataModel](https://user-images.githubusercontent.com/5292311/59014901-5d495800-885b-11e9-99c4-6098aded2823.png)
