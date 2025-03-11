package kg.mega.pg2_hm.services.Impl;

import kg.mega.pg2_hm.models.Car;
import kg.mega.pg2_hm.models.DTO;
import kg.mega.pg2_hm.models.Person;
import kg.mega.pg2_hm.repositories.CarRepo;
import kg.mega.pg2_hm.repositories.PersonRepo;
import kg.mega.pg2_hm.services.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepo carRepo;
    private final PersonRepo personRepo;

    public CarServiceImpl(CarRepo carRepo, PersonRepo personRepo) {
        this.carRepo = carRepo;

        this.personRepo = personRepo;
    }




    @Override
    public Car crateCar(Car car) {
        List<Person> persons = personRepo.findAll();


        Person person = personRepo.findById(car.getPerson().getId()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found"));
        if (car.getPerson() == null || car.getPerson().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Person ID must be provided");
        }
        if (car.getPerson().getId() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid ID: ID must be a positive number");
        }
        if (car.getId() != null && carRepo.existsById(car.getId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        if (car.getId() != null && car.getId() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (carRepo.existsById(car.getPerson().getId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Car for this person already exists");
        }

        car.setPerson(person);
        car.setId(null);


        carRepo.save(car);
        return car;








    }

    @Override
    public List<Car> allCar() {
        return carRepo.findAll();
    }
    @Override
    public Car updateCar(Long id, Car car) {
        Person person = personRepo.findById(car.getPerson().getId()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found"));




        car.setId(id);
        car = carRepo.save(car);



        return car;
    }

    @Override
    public Car deleteCar(DTO id) {
        Long carId = id.getId();
        if(!carRepo.existsById(carId)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found");
        }
        Car car = getByCarId(carId);

        carRepo.deleteById(carId);
        return car;




    }

    @Override
    public Car getByCarId(Long id) {
        return carRepo.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found"));

    }

    @Override
    public List<Car> findAllCarsContainsKeyword(String keyword) {
        List<Car> car = carRepo.findAll();
        for (int i = 0; i < car.size(); i++) {
            if (car.get(i).getModel().contains(keyword)) {
                car.remove(i);
            }
        }
        return car;
    }
}



