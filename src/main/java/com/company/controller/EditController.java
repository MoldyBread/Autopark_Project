package com.company.controller;

import com.company.dao.implementation.BusDaoImpl;
import com.company.dao.implementation.Connector;
import com.company.dao.implementation.DriverDaoImpl;
import com.company.dao.implementation.RouteDaoImpl;
import com.company.entity.Bus;
import com.company.entity.Route;
import com.company.entity.users.Driver;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class EditController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (null == req.getSession().getAttribute("isLogged")
                || (int) req.getSession().getAttribute("isLogged") != 1) {
            resp.sendRedirect("/");
        } else {

            List<Driver> drivers = new DriverDaoImpl(new Connector()).findAll();

            req.getSession().setAttribute("drivers", drivers);

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("jsp/edit.jsp");
            requestDispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action.equals("back")) {

            resp.sendRedirect("/admin");
        } else if (action.equals("lang")) {
            req.getSession().setAttribute("language", req.getParameter("language"));
            resp.sendRedirect("/edit");
        } else {
            long id = Long.valueOf(req.getParameter("busId"));
            long driverId = Long.valueOf(req.getParameter("drivers"));
            long routeId = Long.valueOf(req.getParameter("routes"));
            new BusDaoImpl(new Connector()).update(id, routeId, driverId);
            try {
                new DriverDaoImpl(new Connector()).update(driverId,false);
            } catch (SQLException e) {
            }
            resp.sendRedirect("/edit");
        }
    }
}
