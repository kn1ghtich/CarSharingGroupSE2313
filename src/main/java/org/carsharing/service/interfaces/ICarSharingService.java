package org.carsharing.service.interfaces;

import org.carsharing.models.User;

import java.util.List;

public interface ICarSharingService {



    //String createUser(String name, String surname, String gender);

    boolean  createUser(User user);

    List<User> getAllUsers();

    User getUserById(int id);

    void addCar();
    void showAllCars();

    void showCarByNumber();

}
