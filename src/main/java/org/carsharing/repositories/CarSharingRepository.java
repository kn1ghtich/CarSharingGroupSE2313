package org.carsharing.repositories;

import org.carsharing.models.Car;
import org.carsharing.models.User;
import org.carsharing.repositories.interfaces.ICarSharingRepository;
import org.carsharing.data.interfaces.IDB;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class CarSharingRepository implements ICarSharingRepository {
    private final IDB db;  // Dependency Injection

    public CarSharingRepository(IDB db) {
        System.out.println("Successfull connection");
        this.db = db;
    }


    //1
    @Override
    public boolean createUser(String name, String surname) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "INSERT INTO public.users(name, surname) VALUES (?, ?);";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, name);
            st.setString(2, surname);
            st.execute();
            return true;

        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        } finally {
            try {
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("sql error: " + e.getMessage());
            }
        }
        return false;
    }


    //2
    @Override
    public java.util.List<User> getAllUsers() {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT * FROM public.users;";
            Statement st = con.createStatement();
            java.util.List<User> users = new LinkedList<>();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                User user = new User(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"));
                users.add(user);
            }

            return users;
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        } finally {
            try {
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("sql error: " + e.getMessage());
            }
        }
        return null;
    }


    //3
    @Override
    public User getUserById(int id) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT * FROM public.users WHERE id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("id"), rs.getString("name"), rs.getString("surname"));
            }
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        } finally {
            try {
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("sql error: " + e.getMessage());
            }
        }
        return null;
    }


    //
    public boolean addCar(String carnumber, String brand, String model) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "INSERT INTO public.cars(carnumber, brand, model) VALUES (?, ?, ?);";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, carnumber);
            st.setString(2, brand);
            st.setString(3, model);
            st.execute();

            return true;
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        } finally {
            try {
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("sql error: " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public List<Car> showAllCars(){
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT * FROM public.cars;";
            Statement st = con.createStatement();
            java.util.List<Car> cars = new LinkedList<>();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Car car = new Car(rs.getString("carnumber"),
                        rs.getString("brand"),
                        rs.getString("model"));
                cars.add(car);
            }

            return cars;
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        } finally {
            try {
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("sql error: " + e.getMessage());
            }
        }
        return null;
    }
    /*
@Override
    public User getUser(int id) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "SELECT id,name,surname,gender FROM users WHERE id=?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getBoolean("gender"));
            }
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                System.out.println("sql error: " + e.getMessage());
            }
        }

        return null;
    }
     */
}
