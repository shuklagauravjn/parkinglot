package parkingLot.repository;

import parkingLot.entity.Car;
import parkingLot.entity.Parkinglot;
import parkingLot.entity.Ticket;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface TicketRepository extends JpaRepository<Ticket, String> {
    /**
     *
     * @param id
     * @return
     */
	Ticket findByParkinglot(Parkinglot slotnumber);

    /**
     *
     * @param walletId
     * @return
     */
    List<Ticket> findByCar(Car registrationNumber);
}
