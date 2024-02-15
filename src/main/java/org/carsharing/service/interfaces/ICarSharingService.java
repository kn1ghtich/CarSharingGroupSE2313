package org.carsharing.service.interfaces;

import org.carsharing.models.*;

import java.util.List;

public interface ICarSharingService {



    //String createUser(String name, String surname, String gender);

    boolean  createUser(User user);

    List<User> getAllUsers();


    User getUserById(int id);
    User getUserByEmail(String email);

    boolean addCar(Car car);
    List<Car> getAllCars();

    Car getCarByNumber(String carnumber);

    List<Datehist> getPurchaseHistory(int id);

    boolean rentCar(Rent rent);
}
