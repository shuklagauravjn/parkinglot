package parkingLot.services.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import parkingLot.services.ParkingLotProcessorImpl;
@Service
public class ParkingProcessor implements ParkingLotProcessorImpl {

	@Override
	public int issueTicket(String registrationNumber, String colour) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void returnTicket(int ticketNumber) {
		// TODO Auto-generated method stub

	}

	@Override
	public List findRegistrationNumberByColour(String colour) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int findSlotNumberByRegistrationNumber(String registrationNumber) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int findSlotNumberByCarColour(String colour) {
		// TODO Auto-generated method stub
		return 0;
	}

}
