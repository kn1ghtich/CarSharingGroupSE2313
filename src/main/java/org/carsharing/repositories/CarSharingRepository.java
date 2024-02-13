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
    public boolean createUser(User user) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "INSERT INTO public.users(\n" +
                    " id, name, surname, phonenumber, email, password, money)\n" +
                    "\tVALUES (?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, user.getId());
            st.setString(2, user.getName());
            st.setString(3, user.getSurname());
            st.setString(4, user.getPhonenumber());
            st.setString(5, user.getEmail());
            st.setString(6, user.getPassword());
            st.setInt(7, user.getMoney());

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

    public boolean userExists(User user){
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT * FROM public.users WHERE email = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, user.getEmail());
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()){
                return true;
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
        return false;
    }


    //2
    @Override
    public List<User> getAllUsers() {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT * FROM public.users;";
            Statement st = con.createStatement();
            List<User> users = new LinkedList<>();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                User user = new User(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("phonenumber"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getInt("money")
                );
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
                //User (int id, String name, String surname, String phonenumber, String  email, String  password, int money)
               return new User(rs.getInt("id") , rs.getString("name"),
                       rs.getString("surname"),
                       rs.getString("phonenumber"),
                       rs.getString("email"),
                       rs.getString("password"),
                       rs.getInt("money")
               );
            }
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        } catch (NullPointerException e) {

        } finally {
            try {
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("sql error: " + e.getMessage());
            }
        }
        return null;
    }












    //7
    public boolean addCar(Car car) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "INSERT INTO public.cars(\n" +
                    "\tcarnumber, userid, brand, model, availability, state, price)\n" +
                    "\tVALUES (?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, car.getCarnumber());
            st.setInt(2, car.getUserid());
            st.setString(3, car.getBrand());
            st.setString(4, car.getModel());
            st.setBoolean(5, car.isAvailable());
            st.setBoolean(6, car.isState());
            st.setInt(7, car.getPrice());
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


//8
    @Override
    public List<Car> getAllCars(){
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT * FROM public.cars WHERE state = true;";
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
                        rs.getBoolean("state"),
                        rs.getInt("price")
                );
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



//9
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
                        rs.getBoolean("state"),
                        rs.getInt("price"));
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
}
