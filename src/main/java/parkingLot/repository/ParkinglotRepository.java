package parkingLot.repository;

import parkingLot.entity.Parkinglot;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface ParkinglotRepository extends JpaRepository<Parkinglot, Integer> {
	Parkinglot findBySlotnumber(int slotnumber);
    List<Parkinglot> findByIsavailable(String isAvailable);
}
