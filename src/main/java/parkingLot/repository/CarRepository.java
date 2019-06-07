package parkingLot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import parkingLot.entity.Car;

@RepositoryRestResource
public interface CarRepository extends JpaRepository<Car, String> {
	Car findByRegistrationnumber(String registrationnumber);

	List<Car> findByColour(String colour);
}
