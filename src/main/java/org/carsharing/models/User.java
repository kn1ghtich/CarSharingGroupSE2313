package org.carsharing.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter

public class User {
    private int id;
    private String name;
    private String surname;
    private String email;
    private String phonenumber;
    private String bankname;
    private String cardnumber;
    private String tariff;
    private String carnumber;

}
