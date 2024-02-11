package org.carsharing.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter @Setter

public class Car {
    String carnumber, brand, model;
    public Car (String carnumber, String brand, String model){
        this.carnumber = carnumber;
        this.brand = brand;
        this.model = model;
    }
    @Override
    public String toString(){
        return carnumber + " " + brand + " " + model;
    }
}
