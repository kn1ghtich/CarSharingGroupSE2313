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

    public void getUser() {
        int id = Integer.parseInt(scanner.next());
        service.getUserById();
    }

    public void createUser() {
        String response = service.createUser();
        System.out.println(response);
    }

    public String getAllUsers() {
        service.showAllUsers();
        return null;
    }
///////////////////////
    public void getUserById() {

        service.getUserById();
    }

    public void showPHistoryById() {
    }

    public void rentCar() {
    }

    public void returnCar() {
    }

    public void addCar() {
    }

    public void getAllCars() {
    }



    public void getcarByNumber() {
    }
}
