package kg.mega.pg2_hm.repositories;

import kg.mega.pg2_hm.models.Car;
import kg.mega.pg2_hm.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepo extends JpaRepository<Car,Long> {


}
