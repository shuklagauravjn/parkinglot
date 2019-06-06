package parkingLot.services;

import java.util.List;

import parkingLot.entity.Car;

public interface ParkingLotProcessorImpl {
	public int issueTicket(String registrationNumber, String colour);
	public void returnTicket(int ticketNumber);
	public List<Car> findRegistrationNumberByColour(String colour);
	public int findSlotNumberByRegistrationNumber(String registrationNumber);
	public int findSlotNumberByCarColour(String colour);
}
