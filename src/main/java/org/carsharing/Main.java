package org.carsharing;

import org.carsharing.controllers.CarController;
import org.carsharing.controllers.DateHistController;
import org.carsharing.controllers.UserController;
import org.carsharing.data.PostgresDB;
import org.carsharing.data.interfaces.IDB;
import org.carsharing.repositories.CarRepository;
import org.carsharing.repositories.DateHistRepository;
import org.carsharing.repositories.UserRepository;
import org.carsharing.services.CarService;
import org.carsharing.services.DateHistService;
import org.carsharing.services.UserService;

public class Main {
    public static void main(String[] args) {
        IDB db = PostgresDB.getInstance();

        UserRepository userrepo = UserRepository.getInstance(db);
        CarRepository carrepo = CarRepository.getInstance(db);
        DateHistRepository datehistrepo = DateHistRepository.getInstance(db);

        UserService userService = UserService.getInstance(userrepo);
        DateHistService dateHistService = DateHistService.getInstance(datehistrepo);
        CarService carService = CarService.getInstance(userrepo, carrepo);

        UserController userController = UserController.getInstance(userService);
        CarController carController = CarController.getInstance(carService);
        DateHistController dateHistController = DateHistController.getInstance(dateHistService);

        MyApplication app = MyApplication.getInstance(userController, carController,dateHistController);
        app.start();
    }
}
