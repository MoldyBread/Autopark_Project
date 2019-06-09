package com.autopark.service.impl;

import com.autopark.dao.BusDao;
import com.autopark.entity.Bus;
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
public class BusServiceImplTest {

    @Mock
    private BusDao busDao;
    @InjectMocks
    private BusServiceImpl busService;

    private Bus bus() {
        return new Bus(1L, "AA1234BB", 1L, 1L);
    }

    private Bus[] buses() {
        return new Bus[]{
                bus(),
                bus()
        };
    }


    @Test
    public void shouldFindAll() {
        List<Bus> buses = Arrays.asList(buses());

        when(busDao.findAll()).thenReturn(buses);

        List<Bus> actual = busService.findAll();
        List<Bus> expected = Arrays.asList(buses());

        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindByDriverId() {
        Bus bus = bus();

        Optional<Bus> optionalBus = Optional.ofNullable(bus);

        when(busDao.findByDriverId(1L)).thenReturn(optionalBus);

        Bus actual = busService.findByDriverId(1L).get();
        Bus expected = bus();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindInLimit() {
        List<Bus> buses = Arrays.asList(buses());

        when(busDao.findInLimit(1)).thenReturn(buses);

        List<Bus> actual = busService.findInLimit(1);
        List<Bus> expected = Arrays.asList(buses());

        assertEquals(expected, actual);
    }

    @Test
    public void shouldGetCount() {
        when(busDao.getCount()).thenReturn(5);

        int actual = busService.getCount();
        int expected = 5;

        assertEquals(expected, actual);
    }
}