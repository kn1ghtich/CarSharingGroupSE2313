package org.carsharing.controllers;

import org.carsharing.controllers.interfaces.IPurchaseHistoryController;
import org.carsharing.models.PurchaseHistory;
import org.carsharing.models.Rent;
import org.carsharing.services.PurchaseHistoryService;
import java.util.List;

public class PurchaseHistoryController implements IPurchaseHistoryController {
    private final PurchaseHistoryService purchaseHistoryService;
    private static volatile PurchaseHistoryController instance;

    private PurchaseHistoryController(PurchaseHistoryService purchaseHistoryService) {
        this.purchaseHistoryService = purchaseHistoryService;
    }

    public static PurchaseHistoryController getInstance(PurchaseHistoryService purchaseHistoryService) {
        if (instance == null) {
            synchronized (PurchaseHistoryController.class) {
                if (instance == null) {
                    instance = new PurchaseHistoryController(purchaseHistoryService);
                }
            }
        }
        return instance;
    }

    @Override
    public boolean rentCar(Rent rent) {
        boolean respone = purchaseHistoryService.rentCar(rent);
        return respone;
    }
    @Override
    public boolean returnCar(Rent rent) {
        boolean respone = purchaseHistoryService.returnCar(rent);
        return respone;
    }

    @Override
    public List<PurchaseHistory> GetFullOrderDescriptionById(int id) {
        List<PurchaseHistory> purchaseHistories = purchaseHistoryService.GetFullOrderDescriptionById(id);
        return purchaseHistories;
    }

    @Override
    public List<PurchaseHistory> GetFullOrderDescription() {
        List<PurchaseHistory> purchaseHistories = purchaseHistoryService.GetFullOrderDescription();
        return purchaseHistories;
    }
}
