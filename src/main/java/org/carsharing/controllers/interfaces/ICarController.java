package org.carsharing.controllers.interfaces;

import org.carsharing.dtos.CarDTO;
import org.carsharing.models.Car;
import java.util.List;

public interface ICarController {
    public boolean addCar(Car car);
    public List<CarDTO> getAllCars();
    public CarDTO getCarByNumber(String carnumber);
}
