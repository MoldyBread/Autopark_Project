package com.company.controller;

import com.company.dao.DriverDao;
import com.company.dao.implementation.Connector;
import com.company.dao.implementation.DriverDaoImpl;
import com.company.entity.users.Driver;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class DriversController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (null == req.getSession().getAttribute("isLogged")
                || (int) req.getSession().getAttribute("isLogged") != 1) {
            resp.sendRedirect("/");
        } else {

            DriverDao driverDao = new DriverDaoImpl(new Connector());

            int count = driverDao.getCount();
            count = count % 5 != 0 ? count / 5 + 1 : count / 5;

            int currentPage = 0;
            boolean doRedirect=false;

            try {
                currentPage = Integer.valueOf(req.getParameter("page"));
            } catch (Exception ignored) {
                doRedirect=true;
            }

            if (currentPage < 1 || currentPage > count || doRedirect) {
                resp.sendRedirect("/drivers?page=1");
            }else {

                List<Driver> drivers = driverDao.findInLimit(currentPage);

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

        if (action.equals("back")) {
            resp.sendRedirect("/admin?page=1");
        } else if(action.equals("lang")){
            int currentPage = Integer.valueOf(req.getParameter("page"));
            req.getSession().setAttribute("language", req.getParameter("language"));
            resp.sendRedirect("/drivers?page="+currentPage);
        }else {
            int currentPage = Integer.valueOf(req.getParameter("page"));
            String driverId = req.getParameter("id");
            new DriverDaoImpl(new Connector()).update(Long.valueOf(driverId),false);
            resp.sendRedirect("/drivers?page="+currentPage);
        }
    }
}
