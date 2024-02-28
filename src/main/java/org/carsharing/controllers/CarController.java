package org.carsharing.controllers;

import org.carsharing.dtos.CarDTO;
import org.carsharing.models.Car;
import org.carsharing.models.Rent;
import org.carsharing.services.CarService;

import java.util.LinkedList;
import java.util.List;

public class CarController {
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

    public boolean rentCar(Rent rent) {
        boolean respone = carService.rentCar(rent);
        return respone;
    }

    public boolean returnCar(Rent rent) {
        boolean respone = carService.returnCar(rent);
        return respone;
    }

    public boolean addCar(Car car) {
        boolean feedback = carService.addCar(car);
        return feedback;
    }


    public List<CarDTO> getAllCars() {
        List<Car> cars = carService.getAllCars();
        List<CarDTO> carDTOS = new LinkedList<>();
        for (Car car : cars){
            carDTOS.add(new CarDTO(car));
        }
        return carDTOS;
    }

    public CarDTO getCarByNumber(String carnumber) {
        Car car = carService.getCarByNumber(carnumber);
        if (car == null) {
            return null;
        }
        return new CarDTO(car);
    }
}
