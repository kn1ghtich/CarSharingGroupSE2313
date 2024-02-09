package org.carsharing.repositories.interfaces;

public interface ICarSharingRepository {
    void createUser(String name, String surname);
    void ShowAllUsers();

    /*
    boolean createUser(User user);
    User getUser(int id);
    List<User> getAllUsers();
     */
}
