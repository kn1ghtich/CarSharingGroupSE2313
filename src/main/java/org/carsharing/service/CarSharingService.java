package org.carsharing.service;
import org.carsharing.repositories.*;
import org.carsharing.service.interfaces.ICarSharingService;
import org.carsharing.repositories.interfaces.ICarSharingRepository;
public class CarSharingService implements ICarSharingService {
    ICarSharingRepository repo;

    public CarSharingService(ICarSharingRepository repo){
        this.repo = repo;
    }
}
