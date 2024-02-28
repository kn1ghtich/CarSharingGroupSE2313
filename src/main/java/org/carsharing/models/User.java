package org.carsharing.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter

public class User {
    private int id;
    private String name, surname,  phonenumber, email, password;
    private int money;

    public User (int id, String name, String surname, String phonenumber, String  email, String  password, int money){
        setId(id);
        setName(name);
        setSurname(surname);
        setPhonenumber(phonenumber);
        setEmail(email);
        setPassword(password);
        setMoney(money);
    }

    public User (int id, String email, String password){
        setId(id);
        setEmail(email);
        setPassword(password);
    }
    public String toString(){
        return id + " " + name + " " + surname;
    }
}
