package org.carsharing.repositories;

import org.carsharing.data.interfaces.IDB;
import org.carsharing.models.PurchaseHistory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DateHistRepository {
    private final IDB db;
    private static volatile DateHistRepository instance;
    private DateHistRepository(IDB db){
        this.db = db;
    }

    public static DateHistRepository getInstance(IDB db) {
        if (instance == null) {
            synchronized (UserRepository.class) {
                if (instance == null) {
                    instance = new DateHistRepository(db);
                }
            }
        }
        return instance;
    }

    public List<PurchaseHistory> GetFullOrderDescriptionById(int id){
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT fromdate, userid, carnumber, todate\n" +
                    "\tFROM public.purchasehistory WHERE userid = ?;";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            List<PurchaseHistory> phist = new ArrayList<>();
            while (rs.next()) {
                PurchaseHistory dh = new PurchaseHistory(
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
        }
        return null;
    }
    public List<PurchaseHistory> GetFullOrderDescription(){
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT fromdate, userid, carnumber, todate\n" +
                    "\tFROM public.purchasehistory;";
            PreparedStatement st = con.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            List<PurchaseHistory> phist = new ArrayList<>();
            while (rs.next()) {
                PurchaseHistory dh = new PurchaseHistory(
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
        }
        return null;
    }

}
