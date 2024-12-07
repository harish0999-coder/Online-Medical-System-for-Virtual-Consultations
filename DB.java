package com.java;

import java.sql.*;

public class DB {
    public static Connection con;

    public static Connection getConnection() {
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establish Connection
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hcs?useSSL=false&autoReconnect=true","root","Hareesh@999");
            System.out.println("Database connected successfully!");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
        }
        return con;
    }

    public static void closeConnection() {
        if (con != null) {
            try {
                con.close();
                System.out.println("Database connection closed.");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static ResultSet getResultFromSqlQuery(String SqlQueryString) {
        ResultSet rs = null;
        try {
            if (con == null || con.isClosed()) {
                getConnection();
            }
            rs = con.createStatement().executeQuery(SqlQueryString);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return rs;
    }

    public static int insertUpdateFromSqlQuery(String SqlQueryString) {
        int i = 0;
        try {
            if (con == null || con.isClosed()) {
                getConnection();
            }
            i = con.createStatement().executeUpdate(SqlQueryString);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return i;
    }
}
