package org.carsharing.repositories;

import org.carsharing.data.interfaces.IDB;
import org.carsharing.models.Car;
import org.carsharing.models.Rent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

public class CarRepository {
    //updateCar
    //insert PH
    //rentcar to service /////////////
    private final IDB db;
    private static volatile CarRepository instance;

    private CarRepository(IDB db){
        this.db = db;
    }


    public static CarRepository getInstance(IDB db) {
        if (instance == null) {
            synchronized (UserRepository.class) {
                if (instance == null) {
                    instance = new CarRepository(db);
                }
            }
        }
        return instance;
    }

    public boolean  rentCar(Rent rent){ // car update user and purch add
        Connection con = null;
        try {
            con = db.getConnection();
            String sql1 = "UPDATE public.cars SET availability = false, userid = ? WHERE carnumber = ?;";
            PreparedStatement st1 = con.prepareStatement(sql1);
            st1.setInt(1, rent.getId());
            st1.setString(2, rent.getCarnumber());
            st1.execute();

            String sql3 = "UPDATE public.users SET money = money - 124000, carnumber=? WHERE id = ?;";
            PreparedStatement st3 = con.prepareStatement(sql3);
            st3.setString(1, rent.getCarnumber());
            st3.setInt(2, rent.getId());
            st3.execute();


            String sql2 = "INSERT INTO public.purchasehistory(\n" +
                    "\tfromdate, userid, carnumber, todate)\n" +
                    "\tVALUES (?, ?, ?, ?);";

            PreparedStatement st2 = con.prepareStatement(sql2);

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date  utildate1 = format.parse(rent.getFromdate());
            java.sql.Date sqlFromDate = new java.sql.Date(utildate1.getTime());
            java.util.Date  utildate2 = format.parse(rent.getTodate());
            java.sql.Date sqlToDate = new java.sql.Date(utildate2.getTime());

            st2.setDate(1, sqlFromDate );
            st2.setInt(2, rent.getId());
            st2.setString(3, rent.getCarnumber());
            st2.setDate(4, sqlToDate);

            st2.execute();

            return true;
        } catch (Exception e) {
            System.out.println("sql error: " + e.getMessage());
        }
        return false;
    }


    public boolean returnCar(Rent rent){
        Connection con = null;

        try {
            con = db.getConnection();
            String sql1 = "UPDATE public.cars\n" +
                    "\tSET userid=0, availability=true\n" +
                    "\tWHERE carnumber = ? ;";
            PreparedStatement st1 = con.prepareStatement(sql1);
            st1.setString(1, rent.getCarnumber());
            st1.execute();

            String sql3 = "UPDATE public.users SET money = money, carnumber = null  WHERE id = ?;";
            PreparedStatement st3 = con.prepareStatement(sql3);
            st3.setInt(1, rent.getId());
            st3.execute();
            return true;
        }  catch (Exception e) {
            System.out.println("Date error " + e.getMessage());;
        }
        return false;
    }




    public boolean addCar(Car car) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "INSERT INTO public.cars(\n" +
                    "\tcarnumber, userid, brand, model, availability, price)\n" +
                    "\tVALUES (?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, car.getCarnumber());
            st.setInt(2, car.getUserid());
            st.setString(3, car.getBrand());
            st.setString(4, car.getModel());
            st.setBoolean(5, car.isAvailable());
            st.setInt(6, car.getPrice());
            st.execute();

            return true;
        } catch (Exception e) {
            System.out.println("sql error: " + e.getMessage());
        }
        return false;
    }


    public boolean carExists(Car car){
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT * FROM public.cars WHERE carnumber = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, car.getCarnumber());
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()){
                return true;
            }
        } catch (Exception e) {
            System.out.println("sql error: " + e.getMessage());
        }
        return false;
    }

    public List<Car> getAllCars(){
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT * FROM public.cars WHERE availability = true;";
            Statement st = con.createStatement();
            java.util.List<Car> cars = new LinkedList<>();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Car car = new Car(rs.getInt("id"),
                        rs.getInt("userid"),
                        rs.getString("carnumber"),
                        rs.getString("brand"),
                        rs.getString("model"),
                        rs.getBoolean("availability"),
                        rs.getInt("price")
                );
                cars.add(car);
            }

            return cars;
        } catch (Exception e) {
            System.out.println("sql error: " + e.getMessage());
        }
        return null;
    }



    public Car getCarByNumber(String carnumber){
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT * FROM public.cars WHERE carnumber = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, carnumber);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Car(rs.getInt("id"),
                        rs.getInt("userid"),
                        rs.getString("carnumber"),
                        rs.getString("brand"),
                        rs.getString("model"),
                        rs.getBoolean("availability"),
                        rs.getInt("price"));
            }
        } catch (Exception e) {
            System.out.println("sql error: " + e.getMessage());
        }
        return null;
    }
}
