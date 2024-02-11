package org.carsharing.service;

import org.carsharing.service.interfaces.ICarSharingService;
import org.carsharing.repositories.interfaces.ICarSharingRepository;
import org.carsharing.models.*;

import java.util.Scanner;

public class CarSharingService implements ICarSharingService {
    ICarSharingRepository repo;
    Scanner scanner = new Scanner(System.in);

    public CarSharingService(ICarSharingRepository repo) {
        this.repo = repo;
    }

    @Override
    public String createUser() {
        try {
            String name, surname;
            System.out.print("Please enter name: ");
            name = scanner.next();
            System.out.print("Please enter surname: ");
            surname = scanner.next();
            repo.createUser(name, surname);
            return "User was created successfully! ";
        } catch (Exception e) {
            return "User was not created, exception: " + e.getMessage();
        }

    }

    @Override
    public void showAllUsers() {
        repo.ShowAllUsers();
    }

    public void getUserById() {
        repo.getUserById();
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
