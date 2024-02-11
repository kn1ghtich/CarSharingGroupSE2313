package org.carsharing.repositories.interfaces;
import org.carsharing.models.*;
public interface ICarSharingRepository {
    void createUser(String name, String surname);
    void ShowAllUsers();
    void getUserById();

    /*
    boolean createUser(User user);
    User getUser(int id);
    List<User> getAllUsers();
     */
}
