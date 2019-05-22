package com.company.entity.users;


public class Admin extends User{
    public Admin(Long id, String login, String password) {
        super(id, login, password, UserType.ADMIN);
    }
}
