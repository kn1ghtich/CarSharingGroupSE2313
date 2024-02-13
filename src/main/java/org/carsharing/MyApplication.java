package org.carsharing;

import org.carsharing.controllers.CarSharingController;
import org.carsharing.dtos.UserDTO;
import org.carsharing.models.Car;
import org.carsharing.models.User;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MyApplication {
    private final CarSharingController controller;
    private final Scanner scanner = new Scanner(System.in);
    public MyApplication(CarSharingController controller) {
        this.controller = controller;
    }


    public void start() {
        boolean exit = false;
        System.out.println("Welcome to Car Sharing BangbangTen from group (Bauyrzhan, Azamat, Sabina (BTS)");
        while (!exit) {
            showMenu();

            try {
                System.out.print("Select option: ");
                int option = scanner.nextInt();
                System.out.println();
                switch (option) {
                    case 1:
                        System.out.print("Please enter id: ");
                        int id = Integer.parseInt(scanner.next());
                        System.out.print("Please enter name: ");
                        String name = scanner.next();
                        System.out.print("Please enter surname: ");
                        String surname = scanner.next();
                        System.out.print("Please enter your phone number: ");
                        String phonenumber = scanner.next();
                        System.out.print("Please enter your email: ");
                        String email = scanner.next();
                        System.out.print("Please enter your password: ");
                        String password = scanner.next();
                        System.out.print("Please enter your money amount: ");
                        int money = Integer.parseInt(scanner.next());
                        User user1 = new User(id, name, surname, phonenumber, email, password, money);
                        boolean feedback = controller.createUser(user1);

                        System.out.println(feedback ? name + " " + surname + " was created successfully!" : "C141 :(");
                        break;
                    case 2:
                        List<UserDTO> users = controller.getAllUsers();
                        users.forEach(userDTO -> System.out.println(userDTO));
                        break;
                    case 3:
                        System.out.print("Enter id of User: ");
                        int id3 = Integer.parseInt(scanner.next());
                        UserDTO userDTO = controller.getUserById(id3);
                        System.out.println( (userDTO != null) ? "The user by id = " + userDTO : "C141 :(" );
                        break;
                    case 4:
                        controller.showPHistoryById();
                        break;
                    case 5:
                        controller.rentCar();
                        break;
                    case 6:
                        //"6. Return car"
                        controller.returnCar();
                        break;
                    case 7:
                        //"7. Add Car"
                        System.out.print("Enter carnumber: ");
                        String carnumber7 = scanner.next();
                        System.out.print("Enter brand: ");
                        String brand7 = scanner.next();
                        System.out.print("Enter model: ");
                        String model7 = scanner.next();
                        System.out.print("Enter cars price: ");
                        int price = Integer.parseInt(scanner.next());
                        Car car7 = new Car(0 , 0,  carnumber7, brand7, model7, true, true, price);
                        boolean feedback7 = controller.addCar(car7);
                        System.out.println(feedback7 ? carnumber7 + ": " + brand7 + " " + model7 + " was created successfully!" : "C141 :(");
                        break;
                    case 8:
                        //"8. Get All Cars"
                        controller.showAllCars();
                        break;
                    case 9:
                      //"9. Get car by car number"
                        controller.showCarByNumber();
                        break;
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
    private static void showMenu(){
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
