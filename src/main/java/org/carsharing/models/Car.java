package org.carsharing.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter

public class Car {
    private String carnumber;
    private String brand;
    private String model;
    private String userid;
    private boolean availability; //if nobody take it is true
    private boolean state; // if it is broken
    private int price;

//    public String toString(){
//        return String.format("%s %s %s: %d tenge", brand, model, carNumber, price);
//    }


}
