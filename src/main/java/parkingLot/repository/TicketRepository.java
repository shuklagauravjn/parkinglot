package parkingLot.repository;

import parkingLot.entity.Car;
import parkingLot.entity.Parkinglot;
import parkingLot.entity.Ticket;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
	Ticket findByParkinglot(Parkinglot slotnumber);
    List<Ticket> findByCar(Car registrationNumber);
	//Ticket findOne(Integer integer);
}
