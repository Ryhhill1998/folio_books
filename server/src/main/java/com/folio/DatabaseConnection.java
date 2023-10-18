package com.folio;

import java.sql.*;
import java.util.Scanner;

public class DatabaseConnection {
    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);
        String us = requestUsername(scanner);
        String pass = requestPassword(scanner);

        connectToDatabase(us, pass);

        System.out.println(us + " " + pass);
    }

    static String requestUsername(Scanner scanner){
        System.out.println("Please enter username: ");
        return scanner.nextLine();
    }
    static String requestPassword(Scanner scanner){
        System.out.println("Please enter password: ");
        return scanner.nextLine();
    }

    static void connectToDatabase(String user, String pass) throws SQLException {
        String hostString = "jdbc:mysql://localhost:3306/ecommerce_db";
        String dbUser = "root";
        String dbPass = "root";

        try {
            Connection con = DriverManager.getConnection(hostString, dbUser, dbPass);
            //registerUser(con, user, pass);
            login(con, user, pass);
            con.close();
        }
        catch(SQLException err){
            System.out.println(err.getMessage() + "error");
        }
    }

    static void registerUser(Connection con, String user, String pass) throws SQLException {
        CallableStatement cs = con.prepareCall("{call createNewUser(?, ?)}");

        cs.setString(1, user);
        cs.setString(2, pass);

        cs.execute();

    }

    static void login(Connection con, String user, String pass) throws SQLException {
        boolean recordExists;

        PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE username = ?");
        ps.setString(1, user);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            System.out.println("USERNAME EXISTS");
        }
        else{
            System.out.println("USERNAME DOES NOT EXIST");
        }
    }
}
