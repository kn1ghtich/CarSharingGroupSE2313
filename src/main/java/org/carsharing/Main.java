package org.carsharing;

import org.carsharing.controllers.CarSharingController;
import org.carsharing.repositories.interfaces.ICarSharingRepository;
import org.carsharing.repositories.CarSharingRepository;
import org.carsharing.data.PostgresDB;
import org.carsharing.data.interfaces.IDB;
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
        System.out.println("Welcome to Car Sharing               from group (Bauyrzhan, Azamat, Sabina)");
        while (true) {
            System.out.println("1. Get all users");
            System.out.println("2. Get user by id");
            System.out.println("3. Create user");
            System.out.println("0. Exit");

            try {
                System.out.println("Select option: ");
                int option = scanner.nextInt();
                if (option == 1) {
                    continue;
         //           getAllUsersMenu();
                } else if (option == 2) {
                  //  getUserByIdMenu();
                } else if (option == 3) {
                    controller.createUser();
                } else if (option == 0) {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input must be integer: " + e);
                scanner.nextLine(); // to ignore incorrect input
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            System.out.println("*************************");
        }

    }
}