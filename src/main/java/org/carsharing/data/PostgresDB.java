package org.carsharing.data;

import org.carsharing.data.interfaces.IDB;
import java.sql.*;

public class PostgresDB implements IDB {
    private static volatile PostgresDB instance;
    private PostgresDB(){

    }

    public static PostgresDB getInstance() {
        if (instance == null) {
            synchronized (PostgresDB.class) {
                if (instance == null) {
                    instance = new PostgresDB();
                }
            }
        }
        return instance;
    }

    @Override
    public Connection getConnection() {
        String connectionUrl = "jdbc:postgresql://localhost:5432/ASSING3";
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(connectionUrl, "postgres", "1234");
            return con;
        } catch (Exception e) {
            System.out.println("failed to connect to postgres: " + e.getMessage());
            return null;
        }
    }
}