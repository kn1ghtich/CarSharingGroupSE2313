package org.carsharing.repositories.interfaces;

import org.carsharing.models.PurchaseHistory;
import org.carsharing.models.Rent;

import java.util.List;

public interface IPurchaseHistoryRepository {
    boolean  rentCar(Rent rent);

    boolean returnCar(Rent rent);

    List<PurchaseHistory> GetFullOrderDescriptionById(int id);

    List<PurchaseHistory> GetFullOrderDescription();
}
