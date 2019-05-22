package com.company.dao.implementation;

import com.company.dao.UserDao;
import com.company.entity.users.User;

public abstract class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {
    protected UserDaoImpl(String table, Connector connector) {
        super(table, connector);
    }
}
