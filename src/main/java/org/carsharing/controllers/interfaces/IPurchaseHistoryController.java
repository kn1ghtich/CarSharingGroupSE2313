package org.carsharing.controllers.interfaces;

import org.carsharing.models.PurchaseHistory;
import org.carsharing.models.Rent;

import java.util.List;

public interface IPurchaseHistoryController {
    boolean rentCar(Rent rent);

    boolean returnCar(Rent rent);

    public List<PurchaseHistory> GetFullOrderDescriptionById(int id);
    public List<PurchaseHistory> GetFullOrderDescription();
}
