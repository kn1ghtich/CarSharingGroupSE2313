package org.carsharing.services.interfaces;

import org.carsharing.models.User;

import java.util.List;

public interface IUserService {
    boolean createUser(User user);
    List<User> getAllUsers();
    User getUserById(int id);
    User getUserByEmail(String email);
}
