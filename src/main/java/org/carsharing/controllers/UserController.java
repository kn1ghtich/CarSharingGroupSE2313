package org.carsharing.controllers;

import org.carsharing.controllers.interfaces.IUserController;
import org.carsharing.dtos.UserDTO;
import org.carsharing.models.User;
import org.carsharing.services.UserService;

import java.util.LinkedList;
import java.util.List;

public class UserController implements IUserController {

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

    @Override
    public boolean  createUser(User user) {
        boolean feedback = userService.createUser(user);
        return feedback;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserDTO> userDTOS = new LinkedList<>();
        List<User> users = userService.getAllUsers();

        for (User user : users){
            userDTOS.add(new UserDTO(user));
        }
        return userDTOS;
    }

    @Override
    public UserDTO getUserById(int id) {
        User user = userService.getUserById(id);
        if (user == null){
            return null;
        }
        return new UserDTO(user);
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        User user = userService.getUserByEmail(email);
        if (user == null){
            return null;
        }
        return new UserDTO(user);
    }

    public User getStartUserByEmail(String email) {
        User user = userService.getUserByEmail(email);
        if (user == null){
            return null;
        }
        return user;
    }
}
