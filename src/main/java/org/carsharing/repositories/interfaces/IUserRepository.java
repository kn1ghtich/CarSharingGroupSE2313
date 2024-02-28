package org.carsharing.repositories.interfaces;

import org.carsharing.models.User;

import java.util.List;

public interface IUserRepository {
    boolean createUser(User user);
    boolean userExists(User user);
    List<User> getAllUsers();
    User getUserById(int id);
    User getUserByEmail(String email);
}
