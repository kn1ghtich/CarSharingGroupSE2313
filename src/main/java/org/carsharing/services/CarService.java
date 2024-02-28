package org.carsharing.services;

import org.carsharing.models.Car;
import org.carsharing.models.Rent;
import org.carsharing.models.User;
import org.carsharing.repositories.CarRepository;
import org.carsharing.repositories.UserRepository;
import org.carsharing.services.interfaces.ICarService;

import java.util.List;

public class CarService implements ICarService {
    CarRepository carrepo;
    UserRepository userrepo;
    private static volatile CarService instance;

    private CarService(UserRepository userrepo, CarRepository carrepo) {
        this.userrepo = userrepo;
        this.carrepo = carrepo;
    }

    public static CarService getInstance(UserRepository userrepo, CarRepository carrepo) {
        if (instance == null) {
            synchronized (CarService.class) {
                if (instance == null) {
                    instance = new CarService(userrepo, carrepo);
                }
            }
        }
        return instance;
    }

    @Override
    public boolean addCar(Car car) {
        if (carrepo.carExists(car)) {
            return false;
        }

        boolean response = carrepo.addCar(car);
        return response;
    }

    @Override
    public List<Car> getAllCars() {
        List<Car> cars = carrepo.getAllCars();
        return cars;
    }

    @Override
    public Car getCarByNumber(String carnumber) {
        Car car = carrepo.getCarByNumber(carnumber);
        return car;
    }
}
