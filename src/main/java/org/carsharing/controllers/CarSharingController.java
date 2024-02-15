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
        List<UserDTO> userDTOS = new LinkedList<>(); // empty
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


    public UserDTO getUserByEmail(String email) {
        User user = service.getUserByEmail(email);
        if (user == null){
            return null;
        }
        return new UserDTO(user);
    }





    public List<Datehist>  getPHistoryById(int id) {
        List<Datehist> dh = service.getPurchaseHistory(id);
        return dh;
    }



    public boolean rentCar( Rent rent) {
        boolean respone = service.rentCar(rent);
        return respone;
    }

    public boolean returnCar(Rent rent) {
        boolean respone = service.returnCar(rent);
        return respone;
    }

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
