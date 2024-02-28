package org.carsharing.services;

import org.carsharing.models.PurchaseHistory;
import org.carsharing.repositories.PurchaseHistoryRepository;

import java.util.List;

public class PurchaseHistoryService {
    PurchaseHistoryRepository datehistrepo;
    private static volatile PurchaseHistoryService instance;

    private PurchaseHistoryService(PurchaseHistoryRepository datehistrepo) {
        this.datehistrepo = datehistrepo;
    }

    public static PurchaseHistoryService getInstance(PurchaseHistoryRepository datehistrepo) {
        if (instance == null) {
            synchronized (PurchaseHistoryService.class) {
                if (instance == null) {
                    instance = new PurchaseHistoryService(datehistrepo);
                }
            }
        }
        return instance;
    }

    public List<PurchaseHistory> GetFullOrderDescriptionById(int id) {
        List<PurchaseHistory> dh = datehistrepo.GetFullOrderDescriptionById(id);
        return dh;
    }
    public List<PurchaseHistory> GetFullOrderDescription() {
        List<PurchaseHistory> dh = datehistrepo.GetFullOrderDescription();
        return dh;
    }

}
