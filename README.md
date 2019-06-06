The data model for the parking lot application is as follows:

## Technical Architecture
In this section we are detailing out the technical architecture of the parking lot application.
### Data Model
The data model of the application is as below. There are 2 primary entities. The first one is the car, which has the registraion number and the colour. In this table registration number is the primary key. The colour identifies the car. The second entitty is the ParkingLot. In this entity slot Number is the primary key and the isAvailable identifies if the slot is available or gone. The core entity of the application is the Ticket, which has ticketId as it's primary key. It has the registrtation number of the car and slot number which maps which car is allocated to which slot number in the real world and as primary key and foreign key mapping in the data base world.
![DataModel](https://user-images.githubusercontent.com/5292311/59014901-5d495800-885b-11e9-99c4-6098aded2823.png)
