package com.autopark.controller;


import com.autopark.dao.impl.BusDaoImpl;
import com.autopark.dao.impl.Connector;
import com.autopark.dao.impl.DriverDaoImpl;
import com.autopark.entity.Bus;
import com.autopark.entity.users.Driver;
import com.autopark.service.BusService;
import com.autopark.service.DriverService;
import com.autopark.service.impl.BusServiceImpl;
import com.autopark.service.impl.DriverServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;


public class DriverController extends HttpServlet {

    private static final String IS_LOGGED = "isLogged";
    private static final Logger logger = Logger.getLogger(DriverController.class);
    private static final Connector connector = new Connector();
    private final static DriverService driverService = new DriverServiceImpl(new DriverDaoImpl(connector));

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (null == req.getSession().getAttribute(IS_LOGGED)
                || (int) req.getSession().getAttribute(IS_LOGGED) != 2) {
            resp.sendRedirect("/");
        } else {

            BusService busService = new BusServiceImpl(new BusDaoImpl(connector));


            Driver driver = driverService
                    .findById((Long) req.getSession().getAttribute("id"))
                    .get();

            Optional<Bus> bus = busService.findByDriverId(driver.getId());

            setWorkplaceAttributes(req, driver, bus);

            req.getSession().setAttribute("lang", "en");

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("jsp/driver.jsp");
            requestDispatcher.forward(req, resp);
        }

    }

    private void setWorkplaceAttributes(HttpServletRequest req, Driver driver, Optional<Bus> bus) {
        if (bus.isPresent()) {
            Bus currentBus = bus.get();
            req.getSession().setAttribute("plate", currentBus.getPlate());
            req.getSession().setAttribute("route", currentBus.getRouteId().toString());
            req.getSession().setAttribute("accepted", driver.isAccepted());
        } else {
            req.getSession().setAttribute("route", -1);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        switch (action) {
            case "approve":
                long id = (long) req.getSession().getAttribute("id");
                driverService.update(id, true);
                req.getSession().setAttribute("accepted", true);
                resp.sendRedirect("/driver");
                break;
            case "lang":
                req.getSession().setAttribute("language", req.getParameter("language"));
                resp.sendRedirect("/driver");
                logger.info("Language changed");
                break;
            default:
                req.getSession().setAttribute(IS_LOGGED, null);
                resp.sendRedirect("/");
                logger.info("Logged out");
                break;
        }
    }

}
