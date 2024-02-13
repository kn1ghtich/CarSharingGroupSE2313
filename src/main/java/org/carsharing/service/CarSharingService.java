package org.carsharing.service;

import org.carsharing.models.Car;
import org.carsharing.models.Datehist;
import org.carsharing.models.User;
import org.carsharing.service.interfaces.ICarSharingService;
import org.carsharing.repositories.interfaces.ICarSharingRepository;

import java.util.List;


public class CarSharingService implements ICarSharingService {
    ICarSharingRepository repo;

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

    @Override
    public User getUserByEmail(String email) {
        User user = repo.getUserByEmail(email);
        return user;
    }



    public List<Datehist> getPurchaseHistory(int id){
        List<Datehist> dh = repo.getPurchaseHistory(id);
        return dh;
    }


    public void rentCar() {
    }

    public void returnCar() {
    }

    //7
    public boolean addCar(Car car) {
        if( repo.carExists(car)) {
            return false;
        }

        boolean response = repo.addCar(car);
        return response;
    }

    @Override
    public List<Car> getAllCars() {
        List<Car> cars = repo.getAllCars();
        return cars;
    }

    public Car getCarByNumber(String carnumber) {
        Car car = repo.getCarByNumber(carnumber);
        return car;
    }
}
