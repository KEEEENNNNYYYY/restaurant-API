package com.gastronomiepizza;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    /*protected String connectionUrl;
    protected String user;
    protected String host;
    protected String password;
    protected String port ;
    protected String database;*/

    protected String url = "jdbc:postgresql://localhost:5432/grastropizza?sslmode=disable";
    protected String user = "postgres";
    protected String password = "0000";
    protected int port = 5432;
    protected  String database = "grastropizza";

    public void DataBase() {
        //url = "jdbc:postgresql://" + "localhost" + ":" + port + "/" + database+"?sslmode=disable";
        url = System.getenv("DB_URL");
    }
    /*public Database() {
        this.connectionUrl =  System.getenv("DB_URL");
        this.user =  System.getenv("DB_USER");
        this.host = System.getenv("DB_HOST");
        this.password = System.getenv("DB_PASSWORD");
        this.port = System.getenv("DB_PORT");
        this.database =System.getenv("DB_DATABASE");

    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(connectionUrl, user, password);
    }*/

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }


}
