package parkingLot.services.implementation;

import java.util.ArrayList;
import java.util.List;

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
	public int issueTicket(String registrationNumber, String colour) {
		Car car = new Car();
		car.setColour(colour);
		car.setRegistrationnumber(registrationNumber);
		car = carRepository.save(car);
		String isAvailable=YES;
		List <Parkinglot>parkinglots = parkinglotRepository.findByIsavailable(isAvailable);
		if(parkinglots.size()>0) {
			//this means there are parking which are available
			Parkinglot parkinglot = parkinglots.get(0);
			parkinglot.setIsavailable(NO);
			//Now issue the ticket
			Ticket ticket = new Ticket();
			ticket.setCar(car);
			ticket = ticketRepository.save(ticket);
			return ticket.getTicketid().intValue();
		}else {
				// this means there are no parking slots that are available
				return 0;
			}
	}


	@Override
	public int returnTicket(int ticketNumber) {
		Ticket ticket = ticketRepository.getOne(new Integer(ticketNumber));
		Parkinglot parkinglot= ticket.getParkinglot();
		parkinglot.setIsavailable(YES);
		return parkinglot.getSlotnumber().intValue();
	}

	@Override
	public List<Car> findRegistrationNumberByColour(String colour) {
		List<Car> carList = new ArrayList<Car>();
		List<Car> cars= carRepository.findByColour(colour);
		for(int index= 0;index<cars.size();index++) {
			Car car = cars.get(index);
			List<Ticket> tickets = car.getTickets();
			for(int ticketIndex= 0; ticketIndex<tickets.size(); ticketIndex++) {
				Ticket ticket = tickets.get(ticketIndex);
				if(ticket.getParkinglot().getIsavailable().equals(NO)) {
					// this means these are the cars that are currently parked
					carList.add(car);
				}
			}
		}
		return carList;
	}

	@Override
	public int findSlotNumberByRegistrationNumber(String registrationNumber) {
		Car car = carRepository.findByRegistrationnumber(registrationNumber);
		List<Ticket> tickets = ticketRepository.findByCar(car);
		for(int index = 0;index < tickets.size();index++) {
			Ticket ticket = tickets.get(index);
			Parkinglot parkinglot = ticket.getParkinglot();
			if(parkinglot.getIsavailable().equals(NO)) {
				return parkinglot.getSlotnumber().intValue();
			}
		}
		return 0;
	}

	@Override
	public List<Integer> findSlotNumberByCarColour(String colour) {
		List<Integer> slots = new ArrayList<Integer>();
		List<Car> cars = carRepository.findByColour(colour);
		for(int carsIndex = 0;carsIndex<cars.size();carsIndex++) {
			Car car = cars.get(carsIndex);
			List<Ticket> tickets = ticketRepository.findByCar(car);
			for(int index = 0;index < tickets.size();index++) {
				Ticket ticket = tickets.get(index);
				Parkinglot parkinglot = ticket.getParkinglot();
				if(parkinglot.getIsavailable().equals(NO)) {
					slots.add(parkinglot.getSlotnumber());
				}
			}		
		}
		return slots;
	}
	@Override
	public void setParkinglotSize(int parkingSize) {
		parkinglotRepository.deleteAll();
		List<Parkinglot> parkingLots = new ArrayList<Parkinglot>();
		for(int index=0;index<parkingSize;index++) {
			Parkinglot parkinglot = new Parkinglot();
			parkinglot.setIsavailable(YES);
			parkingLots.add(parkinglot);
		}
		parkinglotRepository.save((Parkinglot) parkingLots);
	}
}
