package com.autopark.controller;

import com.autopark.dao.impl.BusDaoImpl;
import com.autopark.dao.impl.Connector;
import com.autopark.dao.impl.RouteDaoImpl;
import com.autopark.entity.Bus;
import com.autopark.entity.Route;
import com.autopark.service.BusService;
import com.autopark.service.RouteService;
import com.autopark.service.impl.BusServiceImpl;
import com.autopark.service.impl.RouteServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Controller of routes table for admin
 *
 * @author Liash Danylo
 */
public class RoutesController extends HttpServlet {

    private static final String IS_LOGGED = "isLogged";
    private static final Logger logger = Logger.getLogger(EditController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (null == req.getSession().getAttribute(IS_LOGGED)
                || (int) req.getSession().getAttribute(IS_LOGGED) != 1) {
            resp.sendRedirect("/");
        } else {
            Connector connector = new Connector();
            BusService busService = new BusServiceImpl(new BusDaoImpl(connector));
            RouteService routeService = new RouteServiceImpl(new RouteDaoImpl(connector));

            List<Route> routes = routeService.findAll();

            List<Bus> buses = busService.findAll();

            for (Bus bus : buses) {
                if (bus.getRouteId() != -1) {
                    routes.get(getIndexById(routes, bus.getRouteId())).addBus(bus.getPlate());
                }
            }

            req.getSession().setAttribute("routes", routes);

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("jsp/routes.jsp");
            requestDispatcher.forward(req, resp);
        }
    }

    /**
     * Method that returns right index of route by id
     *
     * @param routes routes to  process
     * @param id id of route to find index
     * @return index of route by id in list
     */
    private int getIndexById(List<Route> routes, long id) {
        for (int i = 0; i < routes.size(); i++) {
            if (routes.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action.equals("back")) {
            resp.sendRedirect("/admin?page=1");
        } else {
            req.getSession().setAttribute("language", req.getParameter("language"));
            resp.sendRedirect("/routes");
            logger.info("Language changed");
        }
    }
}
