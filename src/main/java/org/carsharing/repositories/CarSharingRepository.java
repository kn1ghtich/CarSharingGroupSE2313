package org.carsharing.repositories;

import org.carsharing.repositories.interfaces.ICarSharingRepository;
import org.carsharing.data.interfaces.IDB;

import java.sql.*;

public class CarSharingRepository implements ICarSharingRepository {
    private final IDB db;  // Dependency Injection

    public CarSharingRepository(IDB db) {
        System.out.println("Successfull connection");
        this.db = db;
    }
    @Override
    public void createUser(String name, String surname){
        Connection con  = null;
        try {
            con = db.getConnection();
            String sql = "INSERT INTO public.users(name, surname) VALUES (?, ?);";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, name);
            st.setString(2, surname);

            st.execute();
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        } finally {
            try {
                if (con != null) con.close();
            } catch (SQLException e) { System.out.println("sql error: " + e.getMessage()); }
        }
    }

    @Override
    public void ShowAllUsers() {
        Connection con  = null;
        try {
            con = db.getConnection();
            String sql = "SELECT * FROM public.users;";
            Statement st = con.createStatement();

            ResultSet rs =  st.executeQuery(sql);

            while (rs.next()){
                System.out.println(rs.getString("name")
                        + rs.getString("surname"));
            }
        } catch (SQLException e) {
            System.out.println("sql errorjjyf: " + e.getMessage());
        } finally {
            try {
                if (con != null) con.close();
            } catch (SQLException e) { System.out.println("sql error: " + e.getMessage()); }
        }
    }
    /*
    OVerride mtethod ex

    @Override
    public boolean createUser(User user) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "INSERT INTO users(name,surname,gender) VALUES (?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, user.getName());
            st.setString(2, user.getSurname());
            st.setBoolean(3, user.getGender());

            st.execute();

            return true;
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

        return false;
    }
     */
}
