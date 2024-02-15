package org.carsharing.repositories;

import org.carsharing.models.Car;
import org.carsharing.models.Datehist;
import org.carsharing.models.Rent;
import org.carsharing.models.User;
import org.carsharing.repositories.interfaces.ICarSharingRepository;
import org.carsharing.data.interfaces.IDB;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
                    " name, surname, phonenumber, email, password, money)\n" +
                    "\tVALUES (?, ?, ?, ?, ?, ?);";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, user.getName());
            st.setString(2, user.getSurname());
            st.setString(3, user.getPhonenumber());
            st.setString(4, user.getEmail());
            st.setString(5, user.getPassword());
            st.setInt(6, user.getMoney());

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


    @Override
    public User getUserByEmail(String email) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT * FROM public.users WHERE email = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, email);
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

    @Override
    public List<Datehist> getPurchaseHistory(int id){
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT fromdate, userid, carnumber, todate\n" +
                    "\tFROM public.purchasehistory WHERE userid = ?;";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            List<Datehist> phist = new ArrayList<>();
            while (rs.next()) {
                Datehist dh = new Datehist(
                        rs.getDate("fromdate"),
                        rs.getInt("userid") ,
                        rs.getString("carnumber"),
                        rs.getDate("todate")
                        );
                phist.add(dh);
            }

            return phist;
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


    public boolean rentCar(Rent rent){
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
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        } catch (ParseException e) {
            System.out.println("Date error " + e.getMessage());;
        } finally {
            try {
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("sql error: " + e.getMessage());
            }
        }
        return false;
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
