package org.carsharing.service;

import org.carsharing.models.Car;
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
    public boolean  createUser(User user) {
        if (repo.userExists(user)) return false;

        boolean response = repo.createUser(user);
        return response;
    }










    @Override
    public void showAllUsers() {
        List<User> users = repo.getAllUsers();
        users.forEach(user -> System.out.println(user));
    }

    public User showUserById() {
        System.out.print("Enter id of user: ");
        int id = Integer.parseInt(scanner.next());
        User user = repo.getUserById(id);
        System.out.println((user == null ? "User was not found!" : user.toString()));

        return user;

    }

    public void showPHistoryById() {
    }

    public void rentCar() {
    }

    public void returnCar() {
    }
//7
    public void addCar() {
        String carnumber, brand, model;
        System.out.print("Please enter car number: ");
        carnumber = scanner.next();
        System.out.print("Please enter brand: ");
        brand = scanner.next();
        System.out.print("Please enter model: ");
        model = scanner.next();
        boolean response = repo.addCar(carnumber, brand, model);
        System.out.println((response ? "Car was added successfully!" : "Car was not added successfully :("));
    }

    public void showAllCars() {
        List<Car> cars = repo.getAllCars();
        cars.forEach(car -> System.out.println(car));
    }

    public void showCarByNumber(){
        System.out.print("Enter the car number");
        String carnumber = scanner.nextLine();

        Car car = repo.getCarByNumber(carnumber);
        System.out.println(car);
    }
}
