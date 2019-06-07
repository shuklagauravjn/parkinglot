package parkingLot.services;

import java.util.List;

import parkingLot.entity.Car;

public interface ParkingLotProcessorImpl {
	public String issueTicket(String registrationNumber, String colour);
	public int returnTicket(int ticketNumber);
	public String findRegistrationNumberByColour(String colour);
	public int findSlotNumberByRegistrationNumber(String registrationNumber);
	public String findSlotNumberByCarColour(String colour);
	public String setParkinglotSize(int parkingSize);
	public int returnTicketBySlotNumber(int slotNumber);
	public String status();
}
