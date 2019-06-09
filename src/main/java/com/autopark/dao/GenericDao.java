package com.autopark.dao;

import java.util.List;

public interface GenericDao<T> {
    List<T> findAll();
}
