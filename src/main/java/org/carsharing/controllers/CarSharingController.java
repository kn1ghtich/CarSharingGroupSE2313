package org.carsharing.controllers;

import org.carsharing.service.interfaces.ICarSharingService;

import java.util.Scanner;

public class CarSharingController {
    private ICarSharingService service;
    Scanner scanner = new Scanner(System.in);

    public CarSharingController(ICarSharingService service) {
        this.service = service;
    }

    public String getAllUsers() {
        service.showAllUsers();
        return null;
    }

    public String getUser(int id) {
        return null;
    }

    public String createUser() {
        String response = service.createUser();
        System.out.println(response);
        return response;
    }
}
