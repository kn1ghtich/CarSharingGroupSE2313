package org.carsharing.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.carsharing.models.User;

@AllArgsConstructor
@Getter
@Setter
public class UserDTO {
    private int id;
    private String name = "", surname = "", phonenumber = "", email;


    public UserDTO(User user) {
        id = user.getId();
        name = user.getName();
        surname = user.getSurname();
        phonenumber = user.getPhonenumber();
        email = user.getEmail();
    }

    @Override
    public String toString() {
        return "Id: " + id +
                "\n\t | Name: " + name +
                "\n\t | Surname: " + surname +
                "\n\t | Phone number: " + phonenumber +
                "\n\t | Email is: " + email;
    }
}
