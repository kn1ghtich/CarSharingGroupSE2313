package org.carsharing.services;

import org.carsharing.data.interfaces.IDB;
import org.carsharing.models.User;
import org.carsharing.repositories.UserRepository;

import java.util.List;

public class UserService {
    UserRepository userrepo;


    private static volatile UserService instance;

    private UserService(UserRepository userrepo) {
        this.userrepo = userrepo;
    }

    public static UserService getInstance(UserRepository userrepo) {
        if (instance == null) {
            synchronized (UserService.class) {
                if (instance == null) {
                    instance = new UserService(userrepo);
                }
            }
        }
        return instance;
    }

    public boolean createUser(User user) {
        if (userrepo.userExists(user)) return false;

        boolean response = userrepo.createUser(user);
        return response;
    }

    public List<User> getAllUsers() {
        List<User> users = userrepo.getAllUsers();

        return users;
    }

    public User getUserById(int id) {
        User user = userrepo.getUserById(id);
        return user;
    }

    public User getUserByEmail(String email) {
        User user = userrepo.getUserByEmail(email);
        return user;
    }

}
