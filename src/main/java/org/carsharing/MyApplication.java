package org.carsharing;

import org.carsharing.controllers.CarController;
import org.carsharing.controllers.PurchaseHistoryController;
import org.carsharing.controllers.UserController;
import org.carsharing.dtos.CarDTO;
import org.carsharing.dtos.UserDTO;
import org.carsharing.models.Car;
import org.carsharing.models.PurchaseHistory;
import org.carsharing.models.Rent;
import org.carsharing.models.User;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MyApplication {
    private static UserController userController;
    private static CarController carController;
    private static PurchaseHistoryController purchaseHistoryController;
    String login;
    private static final Scanner scanner = new Scanner(System.in);
    private static volatile MyApplication instance;

    private MyApplication(UserController userController, CarController carController, PurchaseHistoryController purchaseHistoryController) {
        MyApplication.userController = userController;
        MyApplication.carController = carController;
        MyApplication.purchaseHistoryController = purchaseHistoryController;
    }

    public static MyApplication getInstance(UserController userController, CarController carController, PurchaseHistoryController purchaseHistoryController) {
        if (instance == null) {
            synchronized (MyApplication.class) {
                if (instance == null) {
                    instance = new MyApplication(userController, carController, purchaseHistoryController);
                }
            }
        }
        return instance;
    }

    public void start() {
        boolean exit = false;
        while (!exit){
            System.out.println("Welcome to Car Sharing!");
            System.out.print("Enter your login: ");
            login = scanner.nextLine();
            System.out.print("Enter your password: ");
            String password = scanner.nextLine();
            if (login.equals("bimbim") && password.equals("1234")){
                adminMenuStart();
            }
            else{
                User user = userController.getStartUserByEmail(login);
                if (user != null){
                    if (user.getEmail().equals(login) && user.getPassword().equals(password)){
                        menuStart();
                    }

                } else {
                    createUser();
                }
            }
        }

    }

    public void menuStart(){
        boolean exit = false;
        while (!exit) {
            showMenu();
            try {
                System.out.print("Select option: ");
                String op = scanner.nextLine();
                int option = Integer.parseInt(op);
                System.out.println();
                switch (option) {
                    case 1:
                        showProfile();
                        break;
                    case 2:
                        rentCar();
                        break;
                    case 3:
                        returnCar();
                        break;
                    case 4:
                        getAllCars();
                        break;
                    case 5:
                        getCarByCarNumber();
                        break;
                    case 6:

                        break;
                    case 0:
                        exit = true;
                        break;
                    default:
                        System.out.println("There is no such option, choose (0-13).");
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

    public void adminMenuStart(){
        boolean exit = false;
        while (!exit) {
            adminMenu();
            try {
                System.out.print("Select option: ");
                String op = scanner.nextLine();
                int option = Integer.parseInt(op);
                System.out.println();
                switch (option) {
                    case 3:
                        getUserById();
                        break;
                    case 4:
                        getUserByEmail();
                        break;
                    case 5:
                        getPurchaseHistoryById();
                        break;
                    case 6:
                        rentCar();
                        break;
                    case 7:
                        returnCar();
                        break;
                    case 8:
                        addCar();
                        break;
                    case 9:
                        getAllCars();
                        break;
                    case 10:
                        getCarByCarNumber();
                        break;
                    case 11:
                        getWholePurchaseHistory();
                        break;
                    case 12:
                        deleteUser();
                        break;
                    case 13:
                        deleteCar();
                        break;
                    case 0:
                        exit = true;
                        break;
                    default:
                        System.out.println("There is no such option, choose (0-13).");
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
        System.out.println("1. ShowProfile");
        System.out.println("2. Show purchase history(your)");
        System.out.println("3. Rent car");
        System.out.println("4. Return car");
        System.out.println("5. Show All Cars");
        System.out.println("6. Show car by car number");
        System.out.println("0. Exit");
    }

    private static void adminMenu(){
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
        System.out.println("11. Show whole purchase history");
        System.out.println("12. Delete user");
        System.out.println("13. Delete car");
        System.out.println("0. Exit");
    }


    private void createUser() {
        String name = enterName();
        String surname = enterSurname();
        String phonenumber = enterPhone();
        String email = enterEmail();
        String password = enterPassword();
        int money = enterMoney();
        User user1 = new User(0, name, surname, phonenumber, email, password, money);
        boolean feedback = userController.createUser(user1);
        System.out.println(feedback ? name + " " + surname + " was created successfully!" : "C141 :(");
    }

    private void getAllUsers() {
        List<UserDTO> users = userController.getAllUsers();
        users.forEach(userDTO -> System.out.println(userDTO));
    }

    private void getUserById() {
        int id3 = enterid();
        UserDTO userDTO = userController.getUserById(id3);
        System.out.println((userDTO != null) ? "The user by id = " + userDTO : "C141 :(");
    }

    public void showProfile(){
        UserDTO userDTO = userController.getUserByEmail(login);
        System.out.println(userDTO);
    }
    private void getUserByEmail() {
        String email10 = enterEmail();
        UserDTO userDTO10 = userController.getUserByEmail(email10);
        System.out.println((userDTO10 != null) ? "The user by id = " + userDTO10 : "C141 :(");
    }

    private void getPurchaseHistoryById() {
        String result = "";
        int id4 = enterid();
        List<PurchaseHistory> dh = purchaseHistoryController.GetFullOrderDescriptionById(id4);
        UserDTO userDTO4 = userController.getUserById(id4);
        if (userDTO4 != null) {
            result += "The purchase history of " + userDTO4.getName() + " " + userDTO4.getSurname() + "\n";
            for (PurchaseHistory purchaseHistory : dh) {
                CarDTO carDTO4 = carController.getCarByNumber(purchaseHistory.getCarnumber());
                purchaseHistory.setCarDTO(carDTO4);
                result += "\t" + purchaseHistory.toString() + "\n";
            }
        } else {
            System.out.println("Purchase History is empty :(");
        }

        System.out.println(result);
    }

    private void rentCar() {
        int id6 = enterid();
        String email6 = enterEmail();
        String password6 = enterPassword();
        List<CarDTO> cars6 = carController.getAllCars();
        cars6.forEach(carDTO -> System.out.println(carDTO));
        System.out.print("Enter car number you want to rent: ");
        String carnumber6 = scanner.nextLine();
        System.out.print("Enter from date (2005-05-27): ");
        String fromdate = scanner.nextLine();
        System.out.print("Enter to date (2005-05-27): ");
        String todate = scanner.nextLine();
        Rent rent = new Rent(id6, email6, password6, carnumber6, fromdate, todate);
        boolean response = purchaseHistoryController.rentCar(rent);
        System.out.println(response ? "You rented car successfully!" : "C141 :(");
    }

    private void returnCar() {
        int id7 = enterid();
        String email7 = enterEmail();
        String password7 = enterPassword();
        System.out.print("Enter car that you rented: ");
        String carnumber7 = scanner.nextLine();
        Rent rent7 = new Rent(id7, email7, password7, carnumber7, "", "");

        boolean response7 = purchaseHistoryController.returnCar(rent7);
        System.out.println(response7 ? "You returned car successfully!" : "You did not return car! C141 :(");
    }

    private void addCar() {
        String carnumber8 = enterCurnumber();
        System.out.print("Enter brand: ");
        String brand7 = scanner.nextLine();
        System.out.print("Enter model: ");
        String model7 = scanner.nextLine();
        System.out.print("Enter cars price: ");
        String pr = scanner.nextLine();
        int price = Integer.parseInt(pr);
        Car car7 = new Car(0, 0, carnumber8, brand7, model7, true, price);
        boolean feedback7 = carController.addCar(car7);

        if (feedback7) {
            System.out.println(carnumber8 + ": " + brand7 + " " + model7 + " was created successfully!");
        } else {
            System.out.println("C141 :(\nCar is already exists:(");
        }
    }

    private void getAllCars() {
        List<CarDTO> cars = carController.getAllCars();
        cars.forEach(carDTO -> System.out.println(carDTO));
    }

    private void getCarByCarNumber() {
        String carnumber = enterCurnumber();
        CarDTO carDTO = carController.getCarByNumber(carnumber);
        System.out.println((carDTO != null) ? "Car: " + carDTO : "No car was found\nC141:(");
    }

    private void getWholePurchaseHistory() {
        StringBuilder result = new StringBuilder();
        List<PurchaseHistory> dh = purchaseHistoryController.GetFullOrderDescription();
        result.append("The purchase history of\n");
        for (PurchaseHistory purchaseHistory : dh) {
            CarDTO carDTO11 = carController.getCarByNumber(purchaseHistory.getCarnumber());
            purchaseHistory.setCarDTO(carDTO11);
            result.append("\t").append(purchaseHistory.toString2()).append("\n");
        }

        System.out.println(result);
    }
    private void deleteUser(){

    }

    private void deleteCar(){

    }

    private String enterName() {
        System.out.print("Enter name: ");
        return scanner.nextLine();
    }

    private String enterSurname() {
        System.out.print("Enter surname: ");
        return scanner.nextLine();
    }

    private String enterPhone() {
        System.out.print("Enter phone number: ");
        return scanner.nextLine();
    }

    private String enterEmail() {
        System.out.print("Enter email: ");
        return scanner.nextLine();
    }

    private String enterPassword() {
        System.out.print("Enter password: ");
        return scanner.nextLine();
    }

    private int enterMoney() {
        System.out.print("Enter money amount: ");
        return Integer.parseInt(scanner.nextLine());
    }

    private int enterid() {
        System.out.print("Enter id: ");
        return Integer.parseInt(scanner.nextLine());
    }

    private String enterCurnumber() {
        System.out.print("Enter carnumber: ");
        return scanner.nextLine();
    }
}
