package com.autopark.dao.impl;

import com.autopark.dao.GenericDao;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Implementation of GenericDao
 *
 * @author Liash Danylo
 */
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
        try (Connection connection = connector.getConnection()) {
            //Try-with-resources
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL + table);

            ResultSet resultSet = preparedStatement.executeQuery();

            return mapResultSetToList(resultSet);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return new ArrayList<>();
    }

    /**
     * Maps result set to list of entities
     *
     * @param resultSet to process
     * @return List of entities
     * @throws SQLException
     */
    public abstract List<T> mapResultSetToList(ResultSet resultSet) throws SQLException;

    /**
     * Maps result set to entity
     *
     * @param resultSet to process
     * @return Entity if result set not null
     * @throws SQLException
     */
    public abstract T mapResultSetToEntity(ResultSet resultSet) throws SQLException;

}
