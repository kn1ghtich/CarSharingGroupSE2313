package org.carsharing.repositories.interfaces;

import org.carsharing.models.User;

import java.util.List;

public interface IUserRepository {
    boolean createUser(User user);
    User getUser(int id);
    List<User> getAllUsers();
}
