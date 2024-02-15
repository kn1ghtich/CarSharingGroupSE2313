package org.carsharing.models;

import lombok.Getter;
import lombok.Setter;


@Setter @Getter
public class Rent {
    int id;
    private String email;
    private String password, carnumber;
    private String  fromdate, todate;

    public Rent(int id, String email, String password,String  carnumber, String  fromdate, String todate){
        setId(id);
        setEmail(email);
        setPassword(password);
        setCarnumber(carnumber);
        setFromdate(fromdate);
        setTodate(todate);
    }
}
