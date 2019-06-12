package com.autopark.controller;

import com.autopark.dao.impl.Connector;
import com.autopark.dao.impl.DriverDaoImpl;
import com.autopark.entity.users.Driver;
import com.autopark.service.DriverService;
import com.autopark.service.impl.DriverServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DriversController extends HttpServlet {

    private static final String IS_LOGGED = "isLogged";
    private static final Logger logger = Logger.getLogger(DriversController.class);
    private static final Connector connector = new Connector();
    private static final DriverService driverService = new DriverServiceImpl(new DriverDaoImpl(connector));

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (null == req.getSession().getAttribute(IS_LOGGED)
                || (int) req.getSession().getAttribute(IS_LOGGED) != 1) {
            resp.sendRedirect("/");
        } else {


            int count = driverService.getCount();
            count = count % 5 != 0 ? count / 5 + 1 : count / 5;

            int currentPage = 0;
            boolean doRedirect = false;

            try {
                currentPage = Integer.valueOf(req.getParameter("page"));
            } catch (Exception ignored) {
                doRedirect = true;
            }

            if (currentPage < 1 || currentPage > count || doRedirect) {
                resp.sendRedirect("/drivers?page=1");
            } else {

                List<Driver> drivers = driverService.findInLimit(currentPage);

                req.getSession().setAttribute("noOfPages", count);
                req.getSession().setAttribute("drivers", drivers);
                req.getSession().setAttribute("page", currentPage);

                RequestDispatcher requestDispatcher = req.getRequestDispatcher("jsp/drivers.jsp");
                requestDispatcher.forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        switch (action) {
            case "back":
                resp.sendRedirect("/admin?page=1");
                break;
            case "lang": {
                int currentPage = Integer.parseInt(req.getParameter("page"));
                req.getSession().setAttribute("language", req.getParameter("language"));
                resp.sendRedirect("/drivers?page=" + currentPage);
                logger.info("Language changed");
                break;
            }
            default: {
                int currentPage = Integer.parseInt(req.getParameter("page"));
                String driverId = req.getParameter("id");
                driverService.update(Long.valueOf(driverId), false);
                resp.sendRedirect("/drivers?page=" + currentPage);
                break;
            }
        }
    }
}
