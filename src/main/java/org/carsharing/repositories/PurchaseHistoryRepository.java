package org.carsharing.repositories;

import org.carsharing.data.interfaces.IDB;
import org.carsharing.models.PurchaseHistory;
import org.carsharing.models.Rent;
import org.carsharing.repositories.interfaces.IPurchaseHistoryRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PurchaseHistoryRepository implements IPurchaseHistoryRepository {
    private final IDB db;
    private static volatile PurchaseHistoryRepository instance;
    private PurchaseHistoryRepository(IDB db){
        this.db = db;
    }

    public static PurchaseHistoryRepository getInstance(IDB db) {
        if (instance == null) {
            synchronized (UserRepository.class) {
                if (instance == null) {
                    instance = new PurchaseHistoryRepository(db);
                }
            }
        }
        return instance;
    }

    @Override
    public boolean  rentCar(Rent rent){
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

    @Override
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

    @Override
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

    @Override
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
