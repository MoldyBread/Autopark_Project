package com.autopark.service.impl;

import com.autopark.dao.AdminDao;
import com.autopark.entity.users.Admin;
import com.autopark.entity.users.UserType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AdminServiceImplTest {

    @Mock
    private AdminDao adminDao;
    @InjectMocks
    private AdminServiceImpl adminService;

    @Test
    public void shouldFindByLoginAndPassword() {

        Admin admin = Admin.builder()
                .withId(1L)
                .withLogin("admin1")
                .withPassword("firstadmin")
                .withUserType(UserType.ADMIN)
                .build();
        Optional<Admin> optionalAdmin = Optional.ofNullable(admin);

        when(adminDao.findByLoginAndPassword("admin1", "firstadmin")).thenReturn(optionalAdmin);

        Admin actual = adminService.findByLoginAndPassword("admin1", "firstadmin").get();
        Admin expected = Admin.builder()
                .withId(1L)
                .withLogin("admin1")
                .withPassword("firstadmin")
                .withUserType(UserType.ADMIN)
                .build();
        ;

        Assert.assertEquals(expected, actual);
    }
}