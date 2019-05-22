package com.company.dao.implementation;

import com.company.dao.GenericDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class GenericDaoImpl<T> implements GenericDao<T> {

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
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL + table);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return mapResultSetToList(resultSet);
            }
        } catch (SQLException e) {
            //logger
            e.printStackTrace();
        }

        return null;
    }

    public abstract List<T> mapResultSetToList(ResultSet resultSet);

    public abstract T mapResultSetToEntity(ResultSet resultSet) throws SQLException;


    //    public Optional<User> findById(Integer id){
//        Connection connection = connector.getConnection();
//        User user = null;
//        try {
//            //Try-with-resources
//            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
//            preparedStatement.setLong(1,id);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()){
//                user = mapResultSetToMessage(resultSet);
//            }
//        } catch (SQLException e) {
//            //logger
//            e.printStackTrace();
//        }
//        return Optional.ofNullable(user);
//    }

}
