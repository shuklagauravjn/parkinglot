Please refer to the read me file before you start using this project.
## Software pre-requisites:
1. Docker
2. Java version 8
3. Maven
## Software Installation Instrtuctions
Step 1: Clone this git repository from https://github.com/shuklagauravjn/parkinglot.git <br />
Step 2: go to bin/setup folder and run the settup.sh file <br />
Step 3: 
 Step 3.1: This command will execute <br/>
 cd ../.. --> this means it will go to the base folder<br />
 mvn clean install --> this will build the application successfully <br />
 >> Check the logs and you should see some logs like this:<br />
 >> Hibernate: alter table ticket drop constraint FK2ii5cq5xek9b6scbnn5h7nwia <br />
 >> Hibernate: alter table ticket drop constraint FK5s6o4c33uj044cbfqxukrf9ki <br />
 >> Hibernate: drop table if exists car cascade <br />
 >> Hibernate: drop table if exists parkinglot cascade <br />
 >> Hibernate: drop table if exists ticket cascade <br />
 >> Hibernate: drop sequence if exists parkinglotsequence <br />
 >> Hibernate: create sequence parkinglotsequence start 1 increment 50 <br />
 >> Hibernate: create table car (registrationnumber varchar(255) not null, colour varchar(255), primary key (registrationnumber)) <br />
>>  Hibernate: create table parkinglot (slotnumber int4 not null, isavailable varchar(255), primary key (slotnumber))
>>  Hibernate: create table ticket (ticketid int4 not null, registrationnumer varchar(255), slotnumer int4, primary key (ticketid)) <br />
>>  Hibernate: alter table ticket add constraint FK2ii5cq5xek9b6scbnn5h7nwia foreign key (registrationnumer) references car <br />
 >> Hibernate: alter table ticket add constraint FK5s6o4c33uj044cbfqxukrf9ki foreign key (slotnumer) references parkinglot <br />
Step 3.2: This means that your data base is setup. <br />
Step 3.3: Check the logs and you will see "Tests run: 5, Failures: 0, Errors: 0, Skipped: 0" This means that the junit test has passed. <br />
Step 3.4: A "BUILD SUCCESS" message means that things are developed properly on your local machine. In case you have any issue, please reach out to me at shuklagauravjn@gmail.com <br />
Step 4: Now go to ../parking_lot folder and run the ./run.sh file <br />
Step 4.1: this will start the docker image and expose port 9090 <br />

## Software Execution Instrtuctions
Step 1:Go to client project https://github.com/shuklagauravjn/parkingLot-REST-Client<br />
Step 2:clone it into a separate Java project and follow instructions as per the project's read me file<br />
## Technical Architecture
In this section we are detailing out the technical architecture of the parking lot application.
### Data Model
The data model of the application is as below. There are 2 primary entities. The first one is the car, which has the registraion number and the colour. In this table registration number is the primary key. The colour identifies the car. The second entitty is the ParkingLot. In this entity slot Number is the primary key and the isAvailable identifies if the slot is available or gone. The core entity of the application is the Ticket, which has ticketId as it's primary key. It has the registrtation number of the car and slot number which maps which car is allocated to which slot number in the real world and as primary key and foreign key mapping in the data base world.
![DataModel](https://user-images.githubusercontent.com/5292311/59014901-5d495800-885b-11e9-99c4-6098aded2823.png)
