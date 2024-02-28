package org.carsharing.services;

import org.carsharing.data.interfaces.IDB;
import org.carsharing.models.User;
import org.carsharing.repositories.UserRepository;
import org.carsharing.services.interfaces.IUserService;

import java.util.List;

public class UserService implements IUserService {
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

    @Override
    public boolean createUser(User user) {
        if (userrepo.userExists(user)) return false;

        boolean response = userrepo.createUser(user);
        return response;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userrepo.getAllUsers();

        return users;
    }

    @Override
    public User getUserById(int id) {
        User user = userrepo.getUserById(id);
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        User user = userrepo.getUserByEmail(email);
        return user;
    }

}
