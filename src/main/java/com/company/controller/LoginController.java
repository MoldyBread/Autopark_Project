package com.company.controller;

import com.company.dao.implementation.AdminDaoImpl;
import com.company.dao.implementation.Connector;
import com.company.dao.implementation.DriverDaoImpl;
import com.company.entity.users.Admin;
import com.company.entity.users.Driver;
import com.company.entity.users.User;
import com.company.service.AdminService;
import com.company.service.DriverService;
import com.company.service.impl.AdminServiceImpl;
import com.company.service.impl.DriverServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class LoginController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(LoginController.class);
    private static final String NOT_FOUND = "notfound";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("jsp/login.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action.equals("lang")) {
            req.getSession().setAttribute("language", req.getParameter("language"));
            resp.sendRedirect("/login");
            logger.info("Language changed");
        } else {
            String login = req.getParameter("login");
            String password = req.getParameter("password");


            Connector connector = new Connector();
            AdminService adminService = new AdminServiceImpl(new AdminDaoImpl(connector));
            Optional<Admin> admin = adminService.findByLoginAndPassword(login,password);
            if (admin.isPresent()) {
                adminForward(req, resp, admin.get());
            } else {
                DriverService driverService = new DriverServiceImpl(new DriverDaoImpl(connector));
                Optional<Driver> driver = driverService.findByLoginAndPassword(login, password);

                if (driver.isPresent()) {
                    driverForward(req, resp, driver.get());
                } else {
                    req.getSession().setAttribute(NOT_FOUND, 1);
                    RequestDispatcher rd = req.getRequestDispatcher("jsp/login.jsp");
                    rd.forward(req, resp);
                    logger.warn("Login or password not found");
                }
            }
        }
    }

    private void adminForward(HttpServletRequest req, HttpServletResponse resp, User loggedUser) throws IOException {
        req.getSession().setAttribute("login", loggedUser.getLogin());
        req.getSession().setAttribute("isLogged", 1);
        req.getSession().setAttribute(NOT_FOUND, 0);

        logger.info("Logged in as admin");

        resp.sendRedirect("/admin?page=1");
    }

    private void driverForward(HttpServletRequest req, HttpServletResponse resp, Driver loggedUser) throws IOException {
        req.getSession().setAttribute("id", loggedUser.getId());
        req.getSession().setAttribute("name", loggedUser.getName());
        req.getSession().setAttribute("surname", loggedUser.getSurname());
        req.getSession().setAttribute("isLogged", 2);
        req.getSession().setAttribute(NOT_FOUND, 0);

        logger.info("Logged in as driver");

        resp.sendRedirect("/driver");
    }
}