package org.carsharing.service.interfaces;

import org.carsharing.models.User;

public interface ICarSharingService {



    //String createUser(String name, String surname, String gender);

    boolean  createUser(User user);

    void showAllUsers();

    User showUserById();

    void addCar();
    void showAllCars();

    void showCarByNumber();

}
