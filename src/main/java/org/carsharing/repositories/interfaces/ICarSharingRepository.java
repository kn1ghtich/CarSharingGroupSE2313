package org.carsharing.repositories.interfaces;

import org.carsharing.models.Car;
import org.carsharing.models.User;
import java.util.List;

public interface ICarSharingRepository {

    boolean createUser(User user);
    boolean userExists(User user);
    List<User> getAllUsers();
    User getUserById(int id);

    boolean addCar(String carnumber, String brand, String model);

    List<Car> getAllCars();

    Car getCarByNumber(String carnumber);




    /*
    boolean createUser(User user);
    User getUser(int id);
    List<User> getAllUsers();
     */
}
