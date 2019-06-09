package com.autopark.service.impl;

import com.autopark.dao.RouteDao;
import com.autopark.entity.Route;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RouteServiceImplTest {

    @Mock
    private RouteDao routeDao;
    @InjectMocks
    private RouteServiceImpl routeService;

    @Test
    public void shouldFindAll() {
        Route[] arrayRoutes = {
                new Route(1L, 23),
                new Route(2L, 10)
        };
        List<Route> routes = Arrays.asList(arrayRoutes);

        when(routeDao.findAll()).thenReturn(routes);

        List<Route> actual = routeService.findAll();
        List<Route> expected = Arrays.asList(arrayRoutes);

        Assert.assertEquals(expected, actual);

    }
}