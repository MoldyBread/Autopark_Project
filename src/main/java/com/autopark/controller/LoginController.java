package com.autopark.controller;

import com.autopark.dao.impl.AdminDaoImpl;
import com.autopark.dao.impl.Connector;
import com.autopark.dao.impl.DriverDaoImpl;
import com.autopark.entity.users.Admin;
import com.autopark.entity.users.Driver;
import com.autopark.entity.users.User;
import com.autopark.service.AdminService;
import com.autopark.service.DriverService;
import com.autopark.service.impl.AdminServiceImpl;
import com.autopark.service.impl.DriverServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * Controller of login view
 *
 * @author Liash Danylo
 */
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
                    notFoundForward(req, resp);
                }
            }
        }
    }

    /**
     * Method for case if user not found
     *
     * @param req request
     * @param resp response
     * @throws ServletException
     * @throws IOException
     */
    private void notFoundForward(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute(NOT_FOUND, 1);
        RequestDispatcher rd = req.getRequestDispatcher("jsp/login.jsp");
        rd.forward(req, resp);
        logger.warn("Login or password not found");
    }

    /**
     * Method for case if the user is admin
     *
     * @param req request
     * @param resp response
     * @throws IOException
     */
    private void adminForward(HttpServletRequest req, HttpServletResponse resp, User loggedUser) throws IOException {
        req.getSession().setAttribute("login", loggedUser.getLogin());
        req.getSession().setAttribute("isLogged", 1);
        req.getSession().setAttribute(NOT_FOUND, 0);

        logger.info("Logged in as admin");

        resp.sendRedirect("/admin?page=1");
    }

    /**
     * Method for case if the user is driver
     *
     * @param req request
     * @param resp response
     * @throws IOException
     */
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