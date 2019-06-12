package com.autopark.dao.impl;

import com.mysql.jdbc.Driver;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * Class that rules with db connection (jdbc)
 *
 * @author Liash Danylo
 */
public class Connector {
    private static final Logger logger = Logger.getLogger(Connector.class);

    private static final String URL = "jdbc:mysql://localhost:3306/autoparkdb";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "admin";

    public Connection getConnection() {
        try {
            DriverManager.registerDriver(new Driver());
            logger.info("Got connection");
            return DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new RuntimeException();
        }
    }


}
