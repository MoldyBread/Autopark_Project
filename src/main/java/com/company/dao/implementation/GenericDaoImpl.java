package com.company.dao.implementation;

import com.company.dao.GenericDao;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class GenericDaoImpl<T> implements GenericDao<T> {

    private static final Logger logger = Logger.getLogger(DriverDaoImpl.class);

    private static final String FIND_ALL = "SELECT * FROM ";

    protected final String table;
    protected final Connector connector;


    protected GenericDaoImpl(String table, Connector connector) {
        this.table = table;
        this.connector = connector;
    }

    @Override
    public List<T> findAll() {
        Connection connection = connector.getConnection();
        T entity = null;
        try {
            //Try-with-resources
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL+table);

            ResultSet resultSet = preparedStatement.executeQuery();

            return mapResultSetToList(resultSet);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return null;
    }

    public abstract List<T> mapResultSetToList(ResultSet resultSet) throws SQLException;

    public abstract T mapResultSetToEntity(ResultSet resultSet) throws SQLException;

}
