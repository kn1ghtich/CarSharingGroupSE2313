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
    public boolean createUser(User user) {
        if (repo.userExists(user)) return false;

        boolean response = repo.createUser(user);
        return response;
    }



    @Override
    public List<User> getAllUsers() {
        List<User> users = repo.getAllUsers();

        return users;
    }







    @Override
    public User getUserById(int id) {
        User user = repo.getUserById(id);
        return user;
    }





    public void showPHistoryById() {
    }

    public void rentCar() {
    }

    public void returnCar() {
    }

    //7
    public boolean addCar(Car car) {
        if( repo.carExists(car)) {
            System.out.println("Car is already exists! ");
            return false;
        }

        boolean response = repo.addCar(car);
        return response;
    }

    public void showAllCars() {
        List<Car> cars = repo.getAllCars();
        cars.forEach(car -> System.out.println(car));
    }

    public void showCarByNumber() {
        System.out.print("Enter the car number");
        String carnumber = scanner.nextLine();

        Car car = repo.getCarByNumber(carnumber);
        System.out.println(car);
    }
}
