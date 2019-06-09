package com.autopark.controller;

import com.autopark.dao.impl.BusDaoImpl;
import com.autopark.dao.impl.Connector;
import com.autopark.dao.impl.DriverDaoImpl;
import com.autopark.dao.impl.RouteDaoImpl;
import com.autopark.entity.Bus;
import com.autopark.entity.Route;
import com.autopark.entity.users.Driver;
import com.autopark.service.BusService;
import com.autopark.service.DriverService;
import com.autopark.service.RouteService;
import com.autopark.service.impl.BusServiceImpl;
import com.autopark.service.impl.DriverServiceImpl;
import com.autopark.service.impl.RouteServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EditController extends HttpServlet {

    private static final String IS_LOGGED = "isLogged";
    private static final Logger logger = Logger.getLogger(EditController.class);
    private static final Connector connector = new Connector();
    private final BusService busService = new BusServiceImpl(new BusDaoImpl(connector));

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (null == req.getSession().getAttribute(IS_LOGGED)
                || (int) req.getSession().getAttribute(IS_LOGGED) != 1) {
            resp.sendRedirect("/");
        } else {
            DriverService driverService = new DriverServiceImpl(new DriverDaoImpl(connector));
            RouteService routeService = new RouteServiceImpl(new RouteDaoImpl(connector));

            List<Driver> drivers = driverService.findFree();
            List<Bus> buses = busService.findAll();
            List<Route> routes = routeService.findAll();

            req.getSession().setAttribute("drivers", drivers);
            req.getSession().setAttribute("buses", buses);
            req.getSession().setAttribute("routes", routes);

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("jsp/edit.jsp");
            requestDispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        switch (action) {
            case "back":
                req.getSession().setAttribute("success", 0);
                resp.sendRedirect("/admin?page=1");
                break;
            case "lang":
                req.getSession().setAttribute("language", req.getParameter("language"));
                resp.sendRedirect("/edit");
                logger.info("Language changed");
                break;
            default:
                editBus(req, resp);
                break;
        }
    }

    private void editBus(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long id = Long.valueOf(req.getParameter("busId"));
        long driverId = Long.valueOf(req.getParameter("drivers"));
        long routeId = Long.valueOf(req.getParameter("routes"));
        busService.update(id, routeId, driverId);
        req.getSession().setAttribute("success", 1);
        logger.info("Bus with id:" + id + " changed");
        resp.sendRedirect("/edit");
    }
}
