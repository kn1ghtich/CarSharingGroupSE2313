package org.carsharing;

import org.carsharing.controllers.CarSharingController;
import org.carsharing.dtos.CarDTO;
import org.carsharing.dtos.UserDTO;
import org.carsharing.models.Car;
import org.carsharing.models.Datehist;
import org.carsharing.models.Rent;
import org.carsharing.models.User;

import java.sql.Date;
import java.text.SimpleDateFormat;
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
                String op = scanner.nextLine();
                int option = Integer.parseInt(op);
                System.out.println();
                switch (option) {
                    case 1:
                        System.out.print("Please enter name: ");
                        String name = scanner.nextLine();
                        System.out.print("Please enter surname: ");
                        String surname = scanner.nextLine();
                        System.out.print("Please enter your phone number: ");
                        String phonenumber = scanner.nextLine();
                        System.out.print("Please enter your email: ");
                        String email = scanner.nextLine();
                        System.out.print("Please enter your password: ");
                        String password = scanner.nextLine();
                        System.out.print("Please enter your money amount: ");
                        int money = Integer.parseInt(scanner.nextLine());
                        User user1 = new User(0 , name, surname, phonenumber, email, password, money);
                        boolean feedback = controller.createUser(user1);

                        System.out.println(feedback ? name + " " + surname + " was created successfully!" : "C141 :(");
                        break;
                    case 2:
                        List<UserDTO> users = controller.getAllUsers();
                        users.forEach(userDTO -> System.out.println(userDTO));
                        break;
                    case 3:
                        System.out.print("Enter id of User: ");
                        int id3 = Integer.parseInt(scanner.nextLine());
                        UserDTO userDTO = controller.getUserById(id3);
                        System.out.println((userDTO != null) ? "The user by id = " + userDTO : "C141 :(");
                        break;
                    case 5:
                        //"4. Show purchase history of user"
                        String result = "";
                        System.out.print("Enter id of user: ");
                        int id4 = Integer.parseInt(scanner.nextLine());
                        List<Datehist> dh = controller.getPHistoryById(id4);
                        UserDTO userDTO4 = controller.getUserById(id4);
                        if (userDTO4 != null) {
                            result += "The purchase history of " + userDTO4.getName() + " " + userDTO4.getSurname() + "\n";
                            for (Datehist datehist : dh) {
                                CarDTO carDTO4 = controller.getCarByNumber(datehist.getCarnumber());
                                datehist.setCarDTO(carDTO4);
                                result += "\t" + datehist.toString();
                            }
                        } else {
                            System.out.println("Purchase History is empty :(");
                        }

                        System.out.println(result);
                        break;
                    case 6:
                        System.out.print("Enter your id: ");
                        int id6 = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter your email: ");
                        String email6 = scanner.nextLine();
                        System.out.print("Enter your password: ");
                        String password6 = scanner.nextLine();
                        List<CarDTO> cars6 = controller.getAllCars();
                        cars6.forEach(carDTO -> System.out.println(carDTO));
                        System.out.print("Enter car number you want to rent: ");
                        String carnumber6 = scanner.nextLine();
                        System.out.print("Enter from date (2005-05-27): ");
                        String fromdate = scanner.nextLine();
                        System.out.print("Enter to date (2005-05-27): ");
                        String todate = scanner.nextLine();
                        Rent rent = new Rent(id6, email6, password6, carnumber6, fromdate, todate);

                        boolean response = controller.rentCar(rent);
                        System.out.println(response ?  "You rented car successfully!": "C141 :(");
                        break;
                    case 7:
                        //"6. Return car"
                        controller.returnCar();
                        break;
                    case 8:
                        //"7. Add Car"
                        System.out.print("Enter carnumber: ");
                        String carnumber7 = scanner.nextLine();

                        System.out.print("Enter brand: ");
                        String brand7 = scanner.nextLine();

                        System.out.print("Enter model: ");
                        String model7 = scanner.nextLine();
                        System.out.print("Enter cars price: ");
                        String pr = scanner.nextLine();
                        int price = Integer.parseInt(pr);
                        Car car7 = new Car(0, 0, carnumber7, brand7, model7, true, true, price);
                        boolean feedback7 = controller.addCar(car7);

                        if (feedback7) {
                            System.out.println(carnumber7 + ": " + brand7 + " " + model7 + " was created successfully!");
                        } else {
                            System.out.println("C141 :(\nCar is already exists:(");
                        }
//                        System.out.println(feedback7 ? carnumber7 + ": " + brand7 + " " + model7 + " was created successfully!" :
//                                "C141 :(\nCar is already exists:(");   //is this that this is not bad
                        break;
                    case 9:
                        //"8. Get All Cars"
                        List<CarDTO> cars = controller.getAllCars();
                        cars.forEach(carDTO -> System.out.println(carDTO));
                        break;
                    case 10:
                        //"9. Get car by car number"
                        System.out.print("Enter car number: ");
                        String carnumber = scanner.next();
                        CarDTO carDTO = controller.getCarByNumber(carnumber);
                        System.out.println((carDTO != null) ? "Car: " + carDTO : "No car was found\nC141:(");
                        break;
                    case 4:
                        System.out.print("Enter email of User: ");
                        String email10 = scanner.next();
                        UserDTO userDTO10 = controller.getUserByEmail(email10);
                        System.out.println((userDTO10 != null) ? "The user by id = " + userDTO10 : "C141 :(");
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

    private static void showMenu() {
        System.out.println("1. Create user");
        System.out.println("2. Show all users");
        System.out.println("3. Show user by id");
        System.out.println("4. Show user by email");
        System.out.println("5. Show purchase history of user");
        System.out.println("6. Rent car");
        System.out.println("7. Return car");
        System.out.println("8. Add Car");
        System.out.println("9. Show All Cars");
        System.out.println("10. Show car by car number");
        System.out.println("0. Exit");
        /*
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
         */
    }
}
