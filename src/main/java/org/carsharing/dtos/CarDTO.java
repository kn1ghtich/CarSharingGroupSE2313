package org.carsharing.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.carsharing.models.Car;


@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class CarDTO {
    private String  carnumber, brand, model;
    private int id, price;
    private boolean available;

    public CarDTO(Car car){
        id = car.getId();
        carnumber = car.getCarnumber();
        brand = car.getBrand();
        model = car.getModel();
        available = car.isAvailable();
        price = car.getPrice();
    }
    @Override
    public String toString(){
        return  "Id: " + id + "\n\t | car number: " +  carnumber + "\n\t | Brand: " + brand + "\n\t | Model: " + model + "\n\t | Price: " +  price;
    }
}
