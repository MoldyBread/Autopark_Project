package com.company.dao.implementation;

import com.mysql.jdbc.Driver;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    private final Logger logger = Logger.getLogger(Connector.class);

    private static final String URL = "jdbc:mysql://localhost:3306/autoparkdb";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "admin";

    public Connection getConnection(){
        try {
            DriverManager.registerDriver(new Driver());
            return DriverManager.getConnection(URL,USER_NAME,PASSWORD);
        }catch (SQLException e){
            logger.error(e.getMessage());
            throw new RuntimeException();
        }
    }


}
