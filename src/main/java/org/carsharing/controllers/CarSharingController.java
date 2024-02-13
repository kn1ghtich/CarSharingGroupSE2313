package org.carsharing.controllers;

import org.carsharing.dtos.CarDTO;
import org.carsharing.dtos.UserDTO;
import org.carsharing.service.interfaces.ICarSharingService;
import org.carsharing.models.*;

import java.util.LinkedList;
import java.util.List;
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

    public List<UserDTO> getAllUsers() {
        List<UserDTO> userDTOS = new LinkedList<>();
        List<User> users = service.getAllUsers();

        for (User user : users){
            userDTOS.add(new UserDTO(user));
        }
        return userDTOS;
    }


    public UserDTO getUserById(int id) {
        User user = service.getUserById(id);
        if (user == null){
            return null;
        }
        return new UserDTO(user);
    }








    public void showPHistoryById() {

    }

    public void rentCar() {
        String response;
    }

    public void returnCar() {    }

    public boolean addCar(Car car) {
        boolean feedback = service.addCar(car);
        return feedback;
    }


    public List<CarDTO> getAllCars() {
        List<Car> cars = service.getAllCars();
        List<CarDTO> carDTOS = new LinkedList<>();

        for (Car car : cars){
            carDTOS.add(new CarDTO(car));
        }
        return carDTOS;
    }

    public CarDTO getCarByNumber(String carnumber) {
        Car car = service.getCarByNumber(carnumber);
        if (car == null) {
            return null;
        }

        return new CarDTO(car);
    }
}
