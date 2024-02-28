package org.carsharing.controllers;

import org.carsharing.dtos.UserDTO;
import org.carsharing.models.User;
import org.carsharing.services.UserService;

import java.util.LinkedList;
import java.util.List;

public class UserController {

    private final UserService userService;
    private static volatile UserController instance;

    private UserController(UserService userService) {
        this.userService = userService;
    }

    public static UserController getInstance(UserService userService) {
        if (instance == null) {
            synchronized (UserController.class) {
                if (instance == null) {
                    instance = new UserController(userService);
                }
            }
        }
        return instance;
    }



    public boolean  createUser(User user) {
        boolean feedback = userService.createUser(user);
        return feedback;
    }

    public List<UserDTO> getAllUsers() {
        List<UserDTO> userDTOS = new LinkedList<>(); // empty
        List<User> users = userService.getAllUsers();

        for (User user : users){ // convert users to usersDTOs but without passwords
            userDTOS.add(new UserDTO(user));
        }
        return userDTOS;
    }


    public UserDTO getUserById(int id) {
        User user = userService.getUserById(id);
        if (user == null){
            return null;
        }
        return new UserDTO(user);
    }


    public UserDTO getUserByEmail(String email) {
        User user = userService.getUserByEmail(email);
        if (user == null){
            return null;
        }
        return new UserDTO(user);
    }

}
