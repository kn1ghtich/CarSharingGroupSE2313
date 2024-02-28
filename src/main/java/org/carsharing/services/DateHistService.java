package org.carsharing.services;

import org.carsharing.models.PurchaseHistory;
import org.carsharing.repositories.DateHistRepository;

import java.util.List;

public class DateHistService {
    DateHistRepository datehistrepo;
    private static volatile DateHistService instance;

    private DateHistService(DateHistRepository datehistrepo) {
        this.datehistrepo = datehistrepo;
    }

    public static DateHistService getInstance(DateHistRepository datehistrepo) {
        if (instance == null) {
            synchronized (DateHistService.class) {
                if (instance == null) {
                    instance = new DateHistService(datehistrepo);
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
