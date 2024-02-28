package org.carsharing.repositories;

import org.carsharing.data.interfaces.IDB;
import org.carsharing.models.Car;
import org.carsharing.repositories.interfaces.ICarRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class CarRepository implements ICarRepository {
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

    @Override
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

    @Override
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

    @Override
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

    @Override
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
