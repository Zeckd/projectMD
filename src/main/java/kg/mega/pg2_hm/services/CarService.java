package kg.mega.pg2_hm.services;

import kg.mega.pg2_hm.models.Car;
import kg.mega.pg2_hm.models.DTO;
import kg.mega.pg2_hm.models.Person;

import java.util.List;

public interface CarService {


    Car crateCar(Car car);

    List<Car> allCar();

    Car updateCar(Long id, Car car);

    Car deleteCar(DTO id);

    Car getByCarId(Long id);

    List<Car> findAllCarsContainsKeyword(String keyword);
}
