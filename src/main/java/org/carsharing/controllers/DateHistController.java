package org.carsharing.controllers;


import org.carsharing.models.PurchaseHistory;
import org.carsharing.services.DateHistService;

import java.util.List;

public class DateHistController {
    private final DateHistService dateHistService;
    private static volatile DateHistController instance;

    private DateHistController(DateHistService dateHistService) {
        this.dateHistService = dateHistService;
    }

    public static DateHistController getInstance(DateHistService dateHistService) {
        if (instance == null) {
            synchronized (DateHistController.class) {
                if (instance == null) {
                    instance = new DateHistController(dateHistService);
                }
            }
        }
        return instance;
    }

    public List<PurchaseHistory> GetFullOrderDescriptionById(int id) {
        List<PurchaseHistory> purchaseHistories = dateHistService.GetFullOrderDescriptionById(id);
        return purchaseHistories;
    }
    public List<PurchaseHistory> GetFullOrderDescription() {
        List<PurchaseHistory> purchaseHistories = dateHistService.GetFullOrderDescription();
        return purchaseHistories;
    }
}
