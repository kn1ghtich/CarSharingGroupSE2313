package org.carsharing.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter @Setter

public class Car {
    private String  carnumber, brand, model;
    private int id, userid, price;
    private boolean available, state;

    public Car (int id, int userid, String carnumber, String brand, String model, boolean available, boolean state, int price){
        setId(id);
        setUserid(userid);
        setCarnumber(carnumber);
        setBrand(brand);
        setModel(model);
        setAvailable(available);
        setState(state);
        setPrice(price);
    }
}

