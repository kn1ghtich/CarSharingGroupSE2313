package org.carsharing.controllers;
import org.carsharing.service.CarSharingService;

public class CarSharingController {
    private CarSharingService service;
    public CarSharingController(CarSharingService service){
        this.service = service;
    }


}
