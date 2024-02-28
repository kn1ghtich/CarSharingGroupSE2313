package org.carsharing.controllers;


import org.carsharing.models.PurchaseHistory;
import org.carsharing.services.PurchaseHistoryService;

import java.util.List;

public class PurchaseHistoryController {
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

    public List<PurchaseHistory> GetFullOrderDescriptionById(int id) {
        List<PurchaseHistory> purchaseHistories = purchaseHistoryService.GetFullOrderDescriptionById(id);
        return purchaseHistories;
    }
    public List<PurchaseHistory> GetFullOrderDescription() {
        List<PurchaseHistory> purchaseHistories = purchaseHistoryService.GetFullOrderDescription();
        return purchaseHistories;
    }
}
