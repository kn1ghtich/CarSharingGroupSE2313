package org.carsharing.controllers.interfaces;

import org.carsharing.dtos.UserDTO;
import org.carsharing.models.User;

import java.util.List;

public interface IUserController {
    public boolean  createUser(User user);
    public List<UserDTO> getAllUsers();
    public UserDTO getUserById(int id);
    public UserDTO getUserByEmail(String email);

}
