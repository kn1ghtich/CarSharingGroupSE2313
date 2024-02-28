package org.carsharing.services;

import org.carsharing.data.interfaces.IDB;
import org.carsharing.models.Car;
import org.carsharing.models.Rent;
import org.carsharing.models.User;
import org.carsharing.repositories.CarRepository;
import org.carsharing.repositories.UserRepository;

import java.util.List;

public class CarService {
    CarRepository carrepo;
    UserRepository userrepo;
    private static volatile CarService instance;

    private CarService(UserRepository userrepo, CarRepository carrepo) {
        this.userrepo = userrepo;
        this.carrepo = carrepo;
    }
    public static CarService getInstance(UserRepository userrepo, CarRepository carrepo) {
        if (instance == null) {
            synchronized (CarService.class) {
                if (instance == null) {
                    instance = new CarService(userrepo, carrepo);
                }
            }
        }
        return instance;
    }
    public boolean rentCar(Rent rent){
        Car car = new Car(rent.getCarnumber());
        if (!carrepo.carExists(car)){
            return carrepo.carExists(car);
        }
        User user = new User(rent.getId(), rent.getEmail(), rent.getPassword());
        if (!userrepo.userExists(user) ){
            User user1 = userrepo.getUserById(rent.getId());
            if(user1.getPassword() != rent.getPassword() || user1.getEmail() != rent.getEmail()){

                if ((user1.getMoney() - car.getPrice()) < 0){
                    System.out.println("You have not enough money!!!!!!!!!");
                    return false;
                }

                return false;
            }
            return userrepo.userExists(user);
        }
// сервис должен апдейтить  а не репозиторий
        boolean responce = carrepo.rentCar(rent);
        return responce;
    }

    public boolean returnCar(Rent rent) {
        boolean responce = carrepo.returnCar(rent);
        return responce;
    }

    //7
    public boolean addCar(Car car) {
        if( carrepo.carExists(car)) {
            return false;
        }

        boolean response = carrepo.addCar(car);
        return response;
    }

    public List<Car> getAllCars() {
        List<Car> cars = carrepo.getAllCars();
        return cars;
    }

    public Car getCarByNumber(String carnumber) {
        Car car = carrepo.getCarByNumber(carnumber);
        return car;
    }
}
