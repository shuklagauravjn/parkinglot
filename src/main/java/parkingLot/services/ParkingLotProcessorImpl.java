package parkingLot.services;

import java.util.List;

public interface ParkingLotProcessorImpl {
	public int issueTicket(String registrationNumber, String colour);
	public void returnTicket(int ticketNumber);
	public List findRegistrationNumberByColour(String colour);
	public int findSlotNumberByRegistrationNumber(String registrationNumber);
	public int findSlotNumberByCarColour(String colour);
}
