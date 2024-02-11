package org.carsharing.service;

import org.carsharing.models.User;
import org.carsharing.service.interfaces.ICarSharingService;
import org.carsharing.repositories.interfaces.ICarSharingRepository;

import java.util.List;
import java.util.Scanner;

public class CarSharingService implements ICarSharingService {
    ICarSharingRepository repo;
    Scanner scanner = new Scanner(System.in);

    public CarSharingService(ICarSharingRepository repo) {
        this.repo = repo;
    }

    @Override
    public String createUser() {
        String name, surname;
        System.out.print("Please enter name: ");
        name = scanner.next();
        System.out.print("Please enter surname: ");
        surname = scanner.next();
        boolean response = repo.createUser(name, surname);
        return ( response ? "User was created successfully!" : "User was not created successfully :(");
    }

    @Override
    public void showAllUsers() {
        List<User> users = repo.getAllUsers();
        users.forEach(user -> System.out.println(user));
    }

    public void showUserById() {
        System.out.print("Enter id of user: ");
        int id = Integer.parseInt(scanner.next());
        User user = repo.getUserById(id);
        System.out.println((user == null ? "User was not found!" : user.toString()));

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
