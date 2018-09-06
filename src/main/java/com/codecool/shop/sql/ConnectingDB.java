package com.codecool.shop.sql;

import java.sql.*;

public class ConnectingDB {

    private static final String url = "jdbc:postgresql://localhost:5432/blackmarket";
    private static final String user = "david";
    private static final String password = "codecool123";


    private static Connection getConnection(){
        try{
            Connection conn = null;
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
            return conn;
        }catch (SQLException ex){
            System.out.println("Somthing woing with your Connection");
        }
        return null;
    }

    public static ResultSet executeQuery(String query){
        try{
            Connection conn = getConnection();
            Statement myst = conn.createStatement();
            ResultSet rs = myst.executeQuery(query);
            return rs;
        }catch (SQLException ex){
            System.out.println("Somthing wrong with your query");
            ex.printStackTrace();
        }
        return null;
    }



 // THIS is just a example
    public static void main(String[] args) throws SQLException {
        ConnectingDB app = new ConnectingDB();
        ResultSet st = app.executeQuery("SELECT * FROM products");
        while(st.next()){
            System.out.println(st.getString("name"));

        }
    }
}