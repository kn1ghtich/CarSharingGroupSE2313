package org.carsharing.controllers;


import org.carsharing.models.Datehist;
import org.carsharing.repositories.DateHistRepository;
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

    public List<Datehist> GetFullOrderDescriptionById(int id) {
        List<Datehist> dh = dateHistService.GetFullOrderDescriptionById(id);
        return dh;
    }
    public List<Datehist> GetFullOrderDescription() {
        List<Datehist> dh = dateHistService.GetFullOrderDescription();
        return dh;
    }
}
