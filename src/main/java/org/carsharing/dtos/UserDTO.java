package org.carsharing.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.carsharing.models.User;

@AllArgsConstructor
@Getter @Setter
public class UserDTO {
    private int id;
    private String name, surname;

    public UserDTO(User user) {
        id = user.getId();
        name = user.getName();
        surname = user.getSurname();
    }
}
