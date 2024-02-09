package org.carsharing;

import org.carsharing.controllers.CarSharingController;
import org.carsharing.data.PostgresDB;
import org.carsharing.data.interfaces.IDB;
import org.carsharing.repositories.CarSharingRepository;
import org.carsharing.repositories.interfaces.ICarSharingRepository;
import org.carsharing.service.CarSharingService;
import org.carsharing.service.interfaces.ICarSharingService;

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
            System.out.println("1. Get all users");
            System.out.println("2. Get user by id");
            System.out.println("3. Create user");
            System.out.println("4. Show purchase history of user");
            System.out.println("5. Add Car");
            System.out.println("6. ");
            System.out.println("7. ");
            System.out.println("8. ");

//            System.out.println();
//            System.out.println();
//            System.out.println();
//            System.out.println();
            System.out.println("0. Exit");

            try {
                System.out.print("Select option: ");
                int option = scanner.nextInt();
                switch (option) {
                    case 1:
                        controller.getAllUsers();
                        break;
                    case 2:
                        //  getUserByIdMenu();
                        break;
                    case 3:
                        controller.createUser();
                        break;
                    case 0:
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
}