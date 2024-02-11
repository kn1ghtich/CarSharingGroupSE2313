package org.carsharing.repositories.interfaces;

import org.carsharing.models.User;
import java.util.List;

import java.awt.*;
import java.util.ArrayList;

public interface ICarSharingRepository {
    boolean createUser(String name, String surname);
    List<User> getAllUsers();
    User getUserById(int id);

    /*
    boolean createUser(User user);
    User getUser(int id);
    List<User> getAllUsers();
     */
}
