package br.com.pedropedreiro.pedropedreiro_api.rdn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private String URL = "jdbc:mysql://localhost:3306/db_pedro_pedreiro";
    private String USER = "root";
    private String PASSWORD = "152127";
//    private String PASSWORD = "";

    public Connection getConnection(){
        try{
            return DriverManager.getConnection(this.URL, this.USER, this.PASSWORD);
        }
        catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }
}