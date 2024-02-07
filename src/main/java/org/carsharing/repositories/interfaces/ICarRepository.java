package org.carsharing.repositories.interfaces;

import org.carsharing.models.Car;
import java.util.List;
public interface ICarRepository {
    boolean createCar(Car car);
    Car getCar(int id);
    List<Car> getAllCars();
}
