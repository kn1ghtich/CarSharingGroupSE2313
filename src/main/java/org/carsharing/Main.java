package org.carsharing;

import org.carsharing.controllers.CarSharingController;
import org.carsharing.data.PostgresDB;
import org.carsharing.data.interfaces.IDB;
import org.carsharing.repositories.CarSharingRepository;
import org.carsharing.repositories.interfaces.ICarSharingRepository;
import org.carsharing.service.CarSharingService;
import org.carsharing.service.interfaces.ICarSharingService;
import org.carsharing.models.*;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        IDB db = new PostgresDB();
        ICarSharingRepository repo = new CarSharingRepository(db);
        ICarSharingService service = new CarSharingService(repo);
        CarSharingController controller = new CarSharingController(service);
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        System.out.println("Welcome to Car Sharing               from group (Bauyrzhan, Azamat, Sabina)");
        while (!exit) {
            showMenu();

            try {
                System.out.print("Select option: ");
                int option = scanner.nextInt();
                switch (option) {
                    case 1:
                        //"1. Create user"
                        controller.createUser();
                        break;
                    case 2:
                        //"2. Get all users"
                        controller.getAllUsers();
                        break;
                    case 3:
                        //"3. Get user by id"
                        controller.getUserById();
                        break;
                    case 4:
                        controller.showPHistoryById();
                        //"4. Show purchase history of user"
                        break;
                    case 5:
                        //"5. Rent car"
                        controller.rentCar();

                        break;
                    case 6:
                        //"6. Return car"
                        controller.returnCar();

                        break;
                    case 7:
                        //"7. Add Car"
                        controller.addCar();
                        break;
                    case 8:
                        //"8. Get All Cars"
                        controller.getAllCars();
                        break;
                    case 9:
                        //"9. Get car by car number"
                        controller.getcarByNumber();
                        break;
                    case 0:
                        //"0. Exit"
                        exit = true;
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input must be integer: " + e);
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            System.out.println("*************************");
        }

    }
    static public void showMenu(){
        System.out.println("1. Create user");
        System.out.println("2. Get all users");
        System.out.println("3. Get user by id");
        System.out.println("4. Show purchase history of user");
        System.out.println("5. Rent car");
        System.out.println("6. Return car");
        System.out.println("7. Add Car");
        System.out.println("8. Get All Cars");
        System.out.println("9. Get car by car number");
        System.out.println("0. Exit");
    }
}