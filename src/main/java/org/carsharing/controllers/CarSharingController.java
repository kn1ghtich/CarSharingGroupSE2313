package org.carsharing.controllers;

import org.carsharing.service.interfaces.ICarSharingService;
import org.carsharing.models.*;
import java.util.Scanner;

public class CarSharingController {
    private ICarSharingService service;
    Scanner scanner = new Scanner(System.in);

    public CarSharingController(ICarSharingService service) {
        this.service = service;
    }

    public void createUser() {
        String response = service.createUser();
        System.out.println(response);
    }

    public void showAllUsers() {
        service.showAllUsers();
    }
    public void showUserById() {
        service.showUserById();
    }

    public void showPHistoryById() {

    }

    public void rentCar() {
        String response;
    }

    public void returnCar() {
        String response;
    }

    public void addCar() {
        String response = service.addCar();
        System.out.println(response);
    }

    public void showAllCars() {
        service.showAllCars();
    }

    public void getcarByNumber() {
    }
}
