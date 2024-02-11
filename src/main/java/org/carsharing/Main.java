package org.carsharing;

import org.carsharing.controllers.CarSharingController;
import org.carsharing.data.PostgresDB;
import org.carsharing.data.interfaces.IDB;
import org.carsharing.repositories.CarSharingRepository;
import org.carsharing.repositories.interfaces.ICarSharingRepository;
import org.carsharing.service.CarSharingService;
import org.carsharing.service.interfaces.ICarSharingService;
import org.carsharing.models.*;

import java.sql.Connection;
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
                System.out.println();
                switch (option) {
                    case 1:
                        //"1. Create user"
                        controller.createUser();
                        break;
                    case 2:
                        //"2. Show all users"
                        controller.showAllUsers();
                        break;
                    case 3:
                        //"3. Get user by id"
                        controller.showUserById();
                        break;
//                    case 4:
//                        controller.showPHistoryById();
//                        //"4. Show purchase history of user"
//                        break;
//                    case 5:
//                        //"5. Rent car"
//                        controller.rentCar();
//
//                        break;
//                    case 6:
//                        //"6. Return car"
//                        controller.returnCar();
//
//                        break;
//                    case 7:
//                        //"7. Add Car"
//                        controller.addCar();
//                        break;
//                    case 8:
//                        //"8. Get All Cars"
//                        controller.getAllCars();
//                        break;
//                    case 9:
//                        //"9. Get car by car number"
//                        controller.getcarByNumber();
//                        break;
                    case 0:
                        //"0. Exit"
                        exit = true;
                        break;
                    default:
                        System.out.println("There is no such option, choose (0-9).");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input must be integer: " + e);
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            System.out.println("*************************\n\n");
        }

    }
    static public void showMenu(){
        System.out.println("1. Create user");
        System.out.println("2. Show all users");
        System.out.println("3. Show user by id");
        System.out.println("4. Show purchase history of user");
        System.out.println("5. Rent car");
        System.out.println("6. Return car");
        System.out.println("7. Add Car");
        System.out.println("8. Show All Cars");
        System.out.println("9. Show car by car number");
        System.out.println("0. Exit");
    }
}