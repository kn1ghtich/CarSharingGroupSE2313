package org.carsharing.service;
import org.carsharing.service.interfaces.ICarSharingService;
import org.carsharing.repositories.interfaces.ICarSharingRepository;

import java.util.Scanner;

public class CarSharingService implements ICarSharingService {
    ICarSharingRepository repo;
    Scanner scanner;

    public CarSharingService(ICarSharingRepository repo){
        this.repo = repo;
    }
    @Override
    public String createUser() {
        String name, surname;

        System.out.println("Please enter name");
        name = scanner.next();
        System.out.println("Please enter surname");
        surname = scanner.next();
        repo.createUser(name , surname);
        return null;
    }
}
