package parkingLot.services.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import parkingLot.entity.Car;
import parkingLot.entity.Parkinglot;
import parkingLot.entity.Ticket;
import parkingLot.repository.CarRepository;
import parkingLot.repository.ParkinglotRepository;
import parkingLot.repository.TicketRepository;
import parkingLot.services.ParkingLotProcessorImpl;

@Service
public class ParkingProcessor implements ParkingLotProcessorImpl {
	final private String YES = "Y";
	final private String NO = "N";
	@Autowired
	private CarRepository carRepository;
	@Autowired
	private ParkinglotRepository parkinglotRepository;
	@Autowired
	private TicketRepository ticketRepository;

	@Override
	public String issueTicket(String registrationNumber, String colour) {

		List<Parkinglot> parkinglots = parkinglotRepository.findByIsavailable(YES);
		if (parkinglots.size() > 0) {
			// this means there are parking which are available
			Parkinglot parkinglot = parkinglots.get(0);
			parkinglot.setIsavailable(NO);
			parkinglotRepository.saveAndFlush(parkinglot);

			Car car = new Car();
			car.setColour(colour);
			car.setRegistrationnumber(registrationNumber);
			carRepository.saveAndFlush(car);

			// Now issue the ticket
			Ticket ticket = new Ticket();
			ticket.setParkinglot(parkinglot);
			ticket.setCar(car);
			ticket = ticketRepository.saveAndFlush(ticket);
			return "Allocated slot number: " + ticket.getParkinglot().getSlotnumber() + "\n";
		} else {
			// this means there are no parking slots that are available
			return "Sorry, parking lot is full\n";
		}
	}

	@Override
	public int returnTicket(int ticketNumber) {
		Optional<Ticket> ticket = ticketRepository.findById(new Integer(ticketNumber));
		// make car parTking slot as available
		Ticket tkt = ticket.get();
		Parkinglot parkinglot = tkt.getParkinglot();
		parkinglot.setIsavailable(YES);
		Car car = tkt.getCar();
		ticketRepository.delete(tkt);
		carRepository.delete(car);
		parkinglotRepository.save(parkinglot);
		return parkinglot.getSlotnumber().intValue();
	}

	@Override
	public int returnTicketBySlotNumber(int slotNumber) {
		Optional<Parkinglot> parkinglot = parkinglotRepository.findById(new Integer(slotNumber));
		// make car parTking slot as available
		Parkinglot parkinglott = parkinglot.get();
		Ticket ticket = ticketRepository.findByParkinglot(parkinglott);
		parkinglott.setIsavailable(YES);

		Car car = ticket.getCar();
		ticketRepository.delete(ticket);
		carRepository.delete(car);
		// parkinglotRepository.save(parkinglott);

		return parkinglott.getSlotnumber().intValue();
	}

	@Override
	public String findRegistrationNumberByColour(String colour) {
		List<Car> cars = carRepository.findByColour(colour);
		String regNumbers = new String();
		for (int index = 0; index < cars.size(); index++) {
			regNumbers += (cars.get(index).getRegistrationnumber()) + ", ";
		}
		int regNumberSize = regNumbers.length();
		if (regNumberSize != 0) {
			return regNumbers.substring(0, regNumberSize - 2);
		} else {
			return regNumbers;
		}

	}

	@Override
	public int findSlotNumberByRegistrationNumber(String registrationNumber) {
		Car car = carRepository.findByRegistrationnumber(registrationNumber);
		List<Ticket> tickets = ticketRepository.findByCar(car);
		for (int index = 0; index < tickets.size(); index++) {
			Ticket ticket = tickets.get(index);
			Parkinglot parkinglot = ticket.getParkinglot();
			if (parkinglot.getIsavailable().equals(NO)) {
				return parkinglot.getSlotnumber().intValue();
			}
		}
		return 0;
	}

	@Override
	public String findSlotNumberByCarColour(String colour) {
		String slots = new String();
		List<Car> cars = carRepository.findByColour(colour);
		for (int carsIndex = 0; carsIndex < cars.size(); carsIndex++) {
			Car car = cars.get(carsIndex);
			List<Ticket> tickets = ticketRepository.findByCar(car);
			for (int index = 0; index < tickets.size(); index++) {
				Ticket ticket = tickets.get(index);
				Parkinglot parkinglot = ticket.getParkinglot();
				if (parkinglot.getIsavailable().equals(NO)) {
					slots += (parkinglot.getSlotnumber().toString()) + ", ";
				}
			}
		}
		int slotsSize = slots.length();
		if (slotsSize != 0) {
			return slots.substring(0, slotsSize - 2);
		} else {
			return slots;
		}

	}

	@Override
	public String setParkinglotSize(int parkingSize) {
		String message = "Created a parking lot with " + parkingSize + " slots\n";
		parkinglotRepository.deleteAll();
		for (int index = 0; index < parkingSize; index++) {
			Parkinglot parkinglot = new Parkinglot();
			parkinglot.setIsavailable(YES);
			parkinglotRepository.save(parkinglot);
		}
		return message;
	}

	@Override
	public String status() {
		String message = "Slot No.\t Registration No \t Colour \n";
		List<Ticket> issuedTickets = ticketRepository.findAll();
		for (int index = 0; index < issuedTickets.size(); index++) {
			Ticket ticket = issuedTickets.get(index);
			message += ticket.getParkinglot().getSlotnumber() + "\t" + ticket.getCar().getRegistrationnumber() + "\t"
					+ ticket.getCar().getColour() + "\n";
		}
		return message;
	}
}
