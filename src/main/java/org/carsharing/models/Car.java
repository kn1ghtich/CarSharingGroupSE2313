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
    private boolean available;
// в пурчасе пихнуть юзер айди и кар намбер
    // пурч должен связывать юзера и кар

    public Car (int id, int userid, String carnumber, String brand, String model, boolean available, int price){
        setId(id);
        setUserid(userid);
        setCarnumber(carnumber);
        setBrand(brand);
        setModel(model);
        setAvailable(available);
        setPrice(price);
    }
    public Car(String carnumber){
        setCarnumber(carnumber);
    }
}

