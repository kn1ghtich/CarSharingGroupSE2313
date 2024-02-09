package org.carsharing.controllers;
import org.carsharing.repositories.interfaces.ICarSharingRepository;
import org.carsharing.service.CarSharingService;
import org.carsharing.service.interfaces.ICarSharingService;

public class CarSharingController {
    private ICarSharingService service;
    public CarSharingController(ICarSharingService service){
        this.service = service;
    }


}
