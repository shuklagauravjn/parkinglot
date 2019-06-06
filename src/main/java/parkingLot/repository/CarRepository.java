package parkingLot.repository;

import parkingLot.entity.Car;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface CarRepository extends JpaRepository<Car, String> {
    /**
     *
     * @param id
     * @return
     */
    Car findByRegistrationnumber(String registrationnumber);

    /**
     *
     * @param walletId
     * @return
     */
    List<Car> findByColour(String colour);
}
