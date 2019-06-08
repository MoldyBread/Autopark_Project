package com.company.controller;

import com.company.dao.implementation.BusDaoImpl;
import com.company.dao.implementation.Connector;
import com.company.dao.implementation.RouteDaoImpl;
import com.company.entity.Bus;
import com.company.entity.Route;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class RoutesController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (null == req.getSession().getAttribute("isLogged")
                || (int) req.getSession().getAttribute("isLogged") != 1) {
            resp.sendRedirect("/");
        } else {
            List<Route> routes = new RouteDaoImpl(new Connector()).findAll();

            List<Bus> buses = new BusDaoImpl(new Connector()).findAll();

            for (Bus bus : buses) {
                if (bus.getRouteId() != -1) {
                    routes.get(Math.toIntExact(bus.getRouteId()) - 1).addBus(bus.getPlate());
                }
            }

            req.getSession().setAttribute("routes", routes);

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("jsp/routes.jsp");
            requestDispatcher.forward(req, resp);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action.equals("back")) {
            resp.sendRedirect("/admin?page=1");
        } else {
            req.getSession().setAttribute("language", req.getParameter("language"));
            resp.sendRedirect("/routes");
        }
    }
}
