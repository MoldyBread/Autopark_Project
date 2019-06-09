package com.autopark.service.impl;

import com.autopark.dao.DriverDao;
import com.autopark.entity.users.Driver;
import com.autopark.entity.users.UserType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DriverServiceImplTest {

    @Mock
    private DriverDao driverDao;
    @InjectMocks
    private DriverServiceImpl driverService;

    private Driver driver() {
        return Driver.builder()
                .withAccepted(true)
                .withSurname("Alex")
                .withName("Alex")
                .withId(1L)
                .withLogin("drvr1")
                .withPassword("12345")
                .withUserType(UserType.DRIVER)
                .build();
    }

    private Driver[] drivers() {
        return new Driver[]{
                driver(),
                driver()
        };
    }

    @Test
    public void shouldFindById() {

        Driver driver = driver();

        Optional<Driver> optionalDriver = Optional.ofNullable(driver);

        when(driverDao.findById(1L)).thenReturn(optionalDriver);

        Driver actual = driverService.findById(1L).get();
        Driver expected = driver();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindByLoginAndPassword() {
        Driver driver = driver();
        Optional<Driver> optionalDriver = Optional.ofNullable(driver);

        when(driverDao.findByLoginAndPassword("drvr1", "12345")).thenReturn(optionalDriver);

        Driver actual = driverService.findByLoginAndPassword("drvr1", "12345").get();
        Driver expected = driver();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindInLimit() {
        List<Driver> drivers = Arrays.asList(drivers());

        when(driverDao.findInLimit(1)).thenReturn(drivers);

        List<Driver> actual = driverService.findInLimit(1);
        List<Driver> expected = Arrays.asList(drivers());

        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindFree() {
        List<Driver> drivers = Arrays.asList(drivers());

        when(driverDao.findFree()).thenReturn(drivers);

        List<Driver> actual = driverService.findFree();
        List<Driver> expected = Arrays.asList(drivers());

        assertEquals(expected, actual);
    }

    @Test
    public void shouldGetCount() {
        when(driverDao.getCount()).thenReturn(5);

        int actual = driverService.getCount();
        int expected = 5;

        assertEquals(expected, actual);
    }

}