package org.carsharing.repositories.interfaces;

import org.carsharing.models.Car;
import org.carsharing.models.Datehist;
import org.carsharing.models.Rent;
import org.carsharing.models.User;
import java.util.List;

public interface ICarSharingRepository {

    boolean createUser(User user);
    boolean userExists(User user);
    List<User> getAllUsers();
    User getUserById(int id);
    User getUserByEmail(String email);
    boolean addCar(Car car);
    boolean carExists(Car car);

    List<Car> getAllCars();

    Car getCarByNumber(String carnumber);
    List<Datehist> getPurchaseHistory(int id);

    boolean rentCar(Rent rent);




    /*
    boolean createUser(User user);
    User getUser(int id);
    List<User> getAllUsers();
     */
}
