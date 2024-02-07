package org.carsharing.repositories;
import org.carsharing.data.interfaces.IDB;
import org.carsharing.models.Car;
import org.carsharing.repositories.interfaces.ICarRepository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
public class CarRepository {

    private final IDB db;  // Dependency Injection

    public CarRepository(IDB db) {
        this.db = db;
    }


    // do some
    //@Override
//    public boolean createCar(User user) {
//        Connection con = null;
//
//        try {
//            con = db.getConnection();
//            String sql = "INSERT INTO users(name,surname,gender) VALUES (?,?,?)";
//            PreparedStatement st = con.prepareStatement(sql);
//
//            st.setString(1, user.getName());
//            st.setString(2, user.getSurname());
//            st.setBoolean(3, user.getGender());
//
//            st.execute();
//
//            return true;
//        } catch (SQLException e) {
//            System.out.println("sql error: " + e.getMessage());
//        } finally {
//            try {
//                if (con != null)
//                    con.close();
//            } catch (SQLException e) {
//                System.out.println("sql error: " + e.getMessage());
//            }
//        }
//
//        return false;
//    }



//    @Override
//    public User getUser(int id) {
//        Connection con = null;
//
//        try {
//            con = db.getConnection();
//            String sql = "SELECT id,name,surname,gender FROM users WHERE id=?";
//            PreparedStatement st = con.prepareStatement(sql);
//
//            st.setInt(1, id);
//
//            ResultSet rs = st.executeQuery();
//            if (rs.next()) {
//                return new User(rs.getInt("id"),
//                        rs.getString("name"),
//                        rs.getString("surname"),
//                        rs.getBoolean("gender"));
//            }
//        } catch (SQLException e) {
//            System.out.println("sql error: " + e.getMessage());
//        } finally {
//            try {
//                if (con != null)
//                    con.close();
//            } catch (SQLException e) {
//                System.out.println("sql error: " + e.getMessage());
//            }
//        }
//
//        return null;
//    }



//    @Override
//    public List<User> getAllUsers() {
//        Connection con = null;
//
//        try {
//            con = db.getConnection();
//            String sql = "SELECT id,name,surname,gender FROM users";
//            Statement st = con.createStatement();
//
//            ResultSet rs = st.executeQuery(sql);
//            List<User> users = new LinkedList<>();
//            while (rs.next()) {
//                User user = new User(rs.getInt("id"),
//                        rs.getString("name"),
//                        rs.getString("surname"),
//                        rs.getBoolean("gender"));
//
//                users.add(user);
//            }
//
//            return users;
//        } catch (SQLException e) {
//            System.out.println("sql error: " + e.getMessage());
//        } finally {
//            try {
//                if (con != null)
//                    con.close();
//            } catch (SQLException e) {
//                System.out.println("sql error: " + e.getMessage());
//            }
//        }
//
//        return null;
//    }
}
