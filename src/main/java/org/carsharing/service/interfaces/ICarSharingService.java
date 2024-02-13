package org.carsharing.service.interfaces;

import org.carsharing.models.*;

import java.util.List;

public interface ICarSharingService {



    //String createUser(String name, String surname, String gender);

    boolean  createUser(User user);

    List<User> getAllUsers();


    User getUserById(int id);

    boolean addCar(Car car);
    void showAllCars();

    void showCarByNumber();

}
