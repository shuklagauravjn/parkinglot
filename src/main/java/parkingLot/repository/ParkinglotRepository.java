package parkingLot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import parkingLot.entity.Parkinglot;

@RepositoryRestResource
public interface ParkinglotRepository extends JpaRepository<Parkinglot, Integer> {
	Parkinglot findBySlotnumber(int slotnumber);

	List<Parkinglot> findByIsavailable(String isAvailable);
}
