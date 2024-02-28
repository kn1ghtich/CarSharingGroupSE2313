package org.carsharing.controllers;

import org.carsharing.controllers.interfaces.ICarController;
import org.carsharing.dtos.CarDTO;
import org.carsharing.models.Car;
import org.carsharing.models.Rent;
import org.carsharing.services.CarService;
import java.util.LinkedList;
import java.util.List;

public class CarController implements ICarController {
    private final CarService carService;
    private static volatile CarController instance;

    private CarController(CarService carService) {
        this.carService = carService;
    }

    public static CarController getInstance(CarService carServiceService) {
        if (instance == null) {
            synchronized (CarController.class) {
                if (instance == null) {
                    instance = new CarController(carServiceService);
                }
            }
        }
        return instance;
    }

    @Override
    public boolean addCar(Car car) {
        boolean feedback = carService.addCar(car);
        return feedback;
    }

    @Override
    public List<CarDTO> getAllCars() {
        List<Car> cars = carService.getAllCars();
        List<CarDTO> carDTOS = new LinkedList<>();
        for (Car car : cars){
            carDTOS.add(new CarDTO(car));
        }
        return carDTOS;
    }
    @Override
    public CarDTO getCarByNumber(String carnumber) {
        Car car = carService.getCarByNumber(carnumber);
        if (car == null) {
            return null;
        }
        return new CarDTO(car);
    }
}
