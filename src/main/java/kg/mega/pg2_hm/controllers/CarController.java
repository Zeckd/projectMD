package kg.mega.pg2_hm.controllers;

import kg.mega.pg2_hm.models.Car;
import kg.mega.pg2_hm.models.DTO;
import kg.mega.pg2_hm.models.Person;
import kg.mega.pg2_hm.services.CarService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {
    private final CarService carService;


    public CarController(CarService carService) {
        this.carService = carService;
    }
    @PostMapping
    public Car createCar(@RequestBody Car car) {
       return carService.crateCar(car);


    }

    @GetMapping("/allCar")
    public List<Car> getAllCar() {
        return carService.allCar();
    }
    @PutMapping("/{id}")
    public Car updateCar(@PathVariable Long id, @RequestBody Car car) {
        return carService.updateCar(id, car);
    }
    @DeleteMapping("/delete")
    public Car deleteCar(@RequestBody DTO id) {
        return carService.deleteCar(id) ;
    }
    @GetMapping("/{id}")
    public Car getByCar(@PathVariable Long id) {
        return carService.getByCarId(id);
    }
    @GetMapping
    public List<Car> findAllCarsContainsKeyword(@RequestParam String keyword) {
        return carService.findAllCarsContainsKeyword(keyword);
    }
}
