package org.carsharing.models;

import lombok.Getter;
import lombok.Setter;
import org.carsharing.controllers.CarSharingController;
import org.carsharing.dtos.CarDTO;

import java.sql.Date;
@Getter @Setter
public class Datehist {
    Date fromdate, todate;
    int userid;
    String carnumber;
    CarDTO carDTO;
    public Datehist(Date fromdate, int userid, String carnumber, Date todate){
        setFromdate(fromdate);
        setUserid(userid);
        setCarnumber(carnumber);
        setTodate(todate);
    }
    @Override
    public String toString(){
        return "From date: " + fromdate + " | " + carDTO.getBrand() +  " " + carDTO.getModel() + " | " + "To date " + todate;
    }
}
