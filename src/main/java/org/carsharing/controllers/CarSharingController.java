package org.carsharing.controllers;

import org.carsharing.dtos.UserDTO;
import org.carsharing.service.interfaces.ICarSharingService;
import org.carsharing.models.*;
import java.util.Scanner;

public class CarSharingController {
    private final ICarSharingService service;
    Scanner scanner = new Scanner(System.in);

    public CarSharingController(ICarSharingService service) {
        this.service = service;
    }

    public boolean  createUser(User user) {
        boolean feedback = service.createUser(user);
        return feedback;
    }

    public void showAllUsers() {
        service.showAllUsers();
    }
    public UserDTO showUserById() {
        User user = service.showUserById();

        return new UserDTO(user);
    }

    public void showPHistoryById() {

    }

    public void rentCar() {
        String response;
    }

    public void returnCar() {    }

    public void addCar() {
        service.addCar();
    }

    public void showAllCars() {
        service.showAllCars();
    }

    public void showCarByNumber() {
        service.showCarByNumber();
    }
}
