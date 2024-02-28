package org.carsharing.services;

import org.carsharing.data.PostgresDB;
import org.carsharing.models.Car;
import org.carsharing.models.PurchaseHistory;
import org.carsharing.models.Rent;
import org.carsharing.models.User;
import org.carsharing.repositories.CarRepository;
import org.carsharing.repositories.PurchaseHistoryRepository;
import org.carsharing.repositories.UserRepository;
import org.carsharing.services.interfaces.IPurchaseHistoryService;

import java.util.List;

public class PurchaseHistoryService implements IPurchaseHistoryService {
    private PurchaseHistoryRepository purchHistoryRepository;
    private CarRepository carrepo;
    private UserRepository userrepo;
    private static volatile PurchaseHistoryService instance;

    private PurchaseHistoryService(PurchaseHistoryRepository purchaseHistoryRepository) {
        this.purchHistoryRepository = purchaseHistoryRepository;
        PostgresDB db = PostgresDB.getInstance();
        carrepo = CarRepository.getInstance(db);
        userrepo = UserRepository.getInstance(db);

    }

    public static PurchaseHistoryService getInstance(PurchaseHistoryRepository purchaseHistoryRepository) {
        if (instance == null) {
            synchronized (PurchaseHistoryService.class) {
                if (instance == null) {
                    instance = new PurchaseHistoryService(purchaseHistoryRepository);
                }
            }
        }
        return instance;
    }

    @Override
    public boolean rentCar(Rent rent) {
        Car car = new Car(rent.getCarnumber());
        if (!carrepo.carExists(car)) {
            return carrepo.carExists(car);
        }
        User user = new User(rent.getId(), rent.getEmail(), rent.getPassword());
        if (!userrepo.userExists(user)) {
            User user1 = userrepo.getUserById(rent.getId());
            if (user1.getPassword() != rent.getPassword() || user1.getEmail() != rent.getEmail()) {

                if ((user1.getMoney() - car.getPrice()) < 0) {
                    System.out.println("You have not enough money!!!!!!!!!");
                    return false;
                }

                return false;
            }
            return userrepo.userExists(user);
        }
        boolean responce = purchHistoryRepository.rentCar(rent);
        return responce;
    }

    @Override
    public boolean returnCar(Rent rent) {
        boolean responce = purchHistoryRepository.returnCar(rent);
        return responce;
    }

    @Override
    public List<PurchaseHistory> GetFullOrderDescriptionById(int id) {
        List<PurchaseHistory> dh = purchHistoryRepository.GetFullOrderDescriptionById(id);
        return dh;
    }

    @Override
    public List<PurchaseHistory> GetFullOrderDescription() {
        List<PurchaseHistory> dh = purchHistoryRepository.GetFullOrderDescription();
        return dh;
    }
}
