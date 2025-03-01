package com.gastronomiepizza;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    protected String url = "jdbc:postgresql://localhost:5432/grastropizza?sslmode=disable";
    protected String user = "postgres";
    protected String password = "0000";
    protected int port = 5432;
    protected  String database = "grastropizza";

    /*protected String url = System.getenv("DB_URL");
    protected String user = System.getenv("DB_USER");
    protected String host = System.getenv("DB_HOST");
    protected String password = System.getenv("DB_PASSWORD");
    protected String port = System.getenv("DB_PORT");
    protected String database =System.getenv("DB_DATABASE");*/

    public void DataBase() {
        //url = "jdbc:postgresql://" + "localhost" + ":" + port + "/" + database+"?sslmode=disable";
        url = System.getenv("DB_URL");
    }
    public Database() {
        /*String host = System.getenv("DB_HOST");
        String port = System.getenv("DB_PORT");
        String database = System.getenv("DB_DATABASE");
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");*/

       /* if (host == null) host = "localhost";
        if (port == null) port = "5432";
        if (database == null) database = "grastropizza";
        if (user == null) user = "postgres";
        if (password == null) password = "0000";*/

        //url = "jdbc:postgresql://" + host + ":" + port + "/" + database + "?sslmode=disable";

        /*this.user = user;
        this.password = password;*/
    }


    public Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
