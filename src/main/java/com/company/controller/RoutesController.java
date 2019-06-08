package com.company.controller;

import com.company.dao.implementation.BusDaoImpl;
import com.company.dao.implementation.Connector;
import com.company.dao.implementation.RouteDaoImpl;
import com.company.entity.Bus;
import com.company.entity.Route;
import com.company.service.BusService;
import com.company.service.RouteService;
import com.company.service.impl.BusServiceImpl;
import com.company.service.impl.RouteServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
