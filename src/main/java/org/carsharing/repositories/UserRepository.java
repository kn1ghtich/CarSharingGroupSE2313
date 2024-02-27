package org.carsharing.repositories;

import org.carsharing.data.interfaces.IDB;
import org.carsharing.models.User;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserRepository {

    private final IDB db;
    private static volatile UserRepository instance;
    private UserRepository(IDB db){
        System.out.println("Successfull connection");
        this.db = db;
    }

    public static UserRepository getInstance(IDB db) {
        if (instance == null) {
            synchronized (UserRepository.class) {
                if (instance == null) {
                    instance = new UserRepository(db);
                }
            }
        }
        return instance;
    }


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
        }
        return false;
    }


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
        }
        return null;
    }



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
        } catch (Exception e) {
            System.out.println("sql error: " + e.getMessage());
        }
        return null;
    }



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
        } catch (Exception e) {
            System.out.println("sql error: " + e.getMessage());
        }
        return null;
    }

}
