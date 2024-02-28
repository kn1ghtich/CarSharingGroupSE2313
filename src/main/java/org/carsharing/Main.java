package org.carsharing;

import org.carsharing.controllers.CarController;
import org.carsharing.controllers.PurchaseHistoryController;
import org.carsharing.controllers.UserController;
import org.carsharing.data.PostgresDB;
import org.carsharing.data.interfaces.IDB;
import org.carsharing.repositories.CarRepository;
import org.carsharing.repositories.PurchaseHistoryRepository;
import org.carsharing.repositories.UserRepository;
import org.carsharing.services.CarService;
import org.carsharing.services.PurchaseHistoryService;
import org.carsharing.services.UserService;

public class Main {
    public static void main(String[] args) {
        IDB db = PostgresDB.getInstance();

        UserRepository userrepo = UserRepository.getInstance(db);
        CarRepository carrepo = CarRepository.getInstance(db);
        PurchaseHistoryRepository datehistrepo = PurchaseHistoryRepository.getInstance(db);

        UserService userService = UserService.getInstance(userrepo);
        PurchaseHistoryService purchaseHistoryService = PurchaseHistoryService.getInstance(datehistrepo);
        CarService carService = CarService.getInstance(userrepo, carrepo);

        UserController userController = UserController.getInstance(userService);
        CarController carController = CarController.getInstance(carService);
        PurchaseHistoryController purchaseHistoryController = PurchaseHistoryController.getInstance(purchaseHistoryService);

        MyApplication app = MyApplication.getInstance(userController, carController, purchaseHistoryController);
        app.start();
    }
}
