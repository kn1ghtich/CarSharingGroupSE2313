package org.carsharing.service.interfaces;
import org.carsharing.models.*;
public interface ICarSharingService {



    //String createUser(String name, String surname, String gender);

    String createUser();

    void showAllUsers();

    void getUserById();
}
