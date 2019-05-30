package com.company.controller;

import com.company.dao.implementation.*;
import com.company.entity.Bus;
import com.company.entity.Route;
import com.company.entity.users.Admin;
import com.company.entity.users.Driver;
import com.company.entity.users.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("jsp/login.jsp");
        requestDispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        Optional<Admin> admin = new AdminDaoImpl(new Connector()).findByLoginAndPassword(login, password);
        if(admin.isPresent()){
            adminForward(req, resp, admin.get());
        }else{
            Optional<Driver> driver = new DriverDaoImpl(new Connector()).findByLoginAndPassword(login, password);
            if(driver.isPresent()) {
                driverForward(req, resp, driver.get());

            }else {
                req.getSession().setAttribute("notfound", 1);
                RequestDispatcher rd = req.getRequestDispatcher("jsp/login.jsp");
                rd.forward(req, resp);

            }
        }
    }

    private void adminForward(HttpServletRequest req, HttpServletResponse resp, User loggedUser) throws ServletException, IOException {
        req.getSession().setAttribute("login", loggedUser.getLogin());
        req.getSession().setAttribute("isLogged",1);
        resp.sendRedirect("/admin");
    }

    private void driverForward(HttpServletRequest req, HttpServletResponse resp, Driver loggedUser) throws ServletException, IOException {
        req.getSession().setAttribute("id", loggedUser.getId());
        req.getSession().setAttribute("name", loggedUser.getName());
        req.getSession().setAttribute("surname", loggedUser.getSurname());
        req.getSession().setAttribute("isLogged",2);

        resp.sendRedirect("/driver");
    }
}