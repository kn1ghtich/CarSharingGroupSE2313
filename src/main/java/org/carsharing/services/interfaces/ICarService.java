package org.carsharing.services.interfaces;

import org.carsharing.models.Car;
import org.carsharing.models.Rent;

import java.util.List;

public interface ICarService {
    boolean addCar(Car car);

    List<Car> getAllCars();

    Car getCarByNumber(String carnumber);
}
