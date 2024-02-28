package org.carsharing.repositories.interfaces;

import org.carsharing.models.Car;

import java.util.List;

public interface ICarRepository {

    boolean addCar(Car car);

    boolean carExists(Car car);

    List<Car> getAllCars();

    Car getCarByNumber(String carnumber);
}
