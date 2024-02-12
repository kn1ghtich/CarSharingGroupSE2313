package org.carsharing;

import org.carsharing.controllers.CarSharingController;
import org.carsharing.data.PostgresDB;
import org.carsharing.data.interfaces.IDB;
import org.carsharing.repositories.CarSharingRepository;
import org.carsharing.repositories.interfaces.ICarSharingRepository;
import org.carsharing.service.CarSharingService;
import org.carsharing.service.interfaces.ICarSharingService;

public class Main {
    public static void main(String[] args) {
        IDB db = new PostgresDB();
        ICarSharingRepository repo = new CarSharingRepository(db);
        ICarSharingService service = new CarSharingService(repo);
        CarSharingController controller = new CarSharingController(service);
        MyApplication app = new MyApplication(controller);
        app.start();
    }
}