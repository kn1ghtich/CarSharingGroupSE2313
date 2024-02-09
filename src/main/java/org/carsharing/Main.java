package org.carsharing;


import org.carsharing.data.PostgresDB;
import org.carsharing.data.interfaces.IDB;


public class Main {
    public static void main(String[] args) {
        IDB db = new PostgresDB();
//        ICarSharingRepository repo = new CarSharingRepository(db);
//        ICarSharingService service = new CarSharingService(repo);
//        CarSharingController controller = new CarSharingController(service);
//        app.start();
    }
}