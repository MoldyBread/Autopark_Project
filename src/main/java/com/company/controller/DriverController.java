package com.company.controller;


import com.company.dao.implementation.BusDaoImpl;
import com.company.dao.implementation.Connector;
import com.company.dao.implementation.DriverDaoImpl;
import com.company.entity.Bus;
import com.company.entity.users.Driver;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;


public class DriverController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(null==req.getSession().getAttribute("isLogged")
                ||(int)req.getSession().getAttribute("isLogged")!=2){
            resp.sendRedirect("/");
        }else {

            Driver driver =
                    new DriverDaoImpl(new Connector()).findById((Long) req.getSession().getAttribute("id")).get();

            Optional<Bus> bus = new BusDaoImpl(new Connector()).findByDriverId(driver.getId());

            if (bus.isPresent()) {
                Bus cureentBus = bus.get();
                req.getSession().setAttribute("plate", cureentBus.getPlate());
                req.getSession().setAttribute("route", cureentBus.getRouteId().toString());
                req.getSession().setAttribute("accepted", driver.isAccepted());
            } else {
                req.getSession().setAttribute("route", -1);
            }

            req.getSession().setAttribute("lang","en");

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("jsp/driver.jsp");
            requestDispatcher.forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if(action.equals("approve")) {
                long id = (long) req.getSession().getAttribute("id");
                new DriverDaoImpl(new Connector()).update(id, true);
                req.getSession().setAttribute("accepted", true);

            resp.sendRedirect("/driver");
        }else if(action.equals("lang")){
            req.getSession().setAttribute("language",req.getParameter("language"));
            resp.sendRedirect("/driver");
        }
        else{
            req.getSession().setAttribute("isLogged",null);
            resp.sendRedirect("/");
        }
    }

}
