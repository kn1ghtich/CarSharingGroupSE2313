package org.carsharing.controllers;

import org.carsharing.models.User;
import org.carsharing.repositories.interfaces.IUserRepository;

import java.util.List;

public class UserController {
    private final IUserRepository repo;

    public UserController(IUserRepository repo) {
        this.repo = repo;
    }

    public String createUser(String name, String surname, String gender) {
        //boolean male = gender.toLowerCase().equals("male");
//        User user = new User(name, surname, male);
//
//        boolean created = repo.createUser(user);
//
//        return (created ? "User was created!" : "User creation was failed!");
        return "bim";
    }

    public String getUser(int id) {
        User user = repo.getUser(id);

        return (user == null ? "User was not found!" : user.toString());
    }

    public String getAllUsers() {
        List<User> users = repo.getAllUsers();

        StringBuilder response = new StringBuilder();
        for (User user : users) {
            response.append(user.toString()).append("\n");
        }

        return response.toString();
    }
}