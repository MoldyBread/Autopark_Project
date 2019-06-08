package com.company.controller;

import com.company.dao.BusDao;
import com.company.dao.implementation.AdminDaoImpl;
import com.company.dao.implementation.BusDaoImpl;
import com.company.dao.implementation.Connector;
import com.company.entity.Bus;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AdminController extends HttpServlet {

    private static final String IS_LOGGED = "isLogged";
    private static final Logger logger = Logger.getLogger(AdminController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (null == req.getSession().getAttribute(IS_LOGGED)
                || (int) req.getSession().getAttribute(IS_LOGGED) != 1) {
            resp.sendRedirect("/");
        } else {
            BusDao busDao = new BusDaoImpl(new Connector());

            int count = busDao.getCount();
            count = count % 5 != 0 ? count / 5 + 1 : count / 5;

            int currentPage = 0;
            boolean doRedirect = false;

            try {
                currentPage = Integer.valueOf(req.getParameter("page"));
            } catch (Exception e) {
                doRedirect = true;
                logger.error(e.getMessage());
            }

            if (currentPage < 1 || currentPage > count || doRedirect) {
                resp.sendRedirect("/admin?page=1");
            } else {

                List<Bus> buses = busDao.findInLimit(currentPage);


                req.getSession().setAttribute("buses", buses);
                req.getSession().setAttribute("noOfPages", count);
                req.getSession().setAttribute("page", currentPage);

                RequestDispatcher requestDispatcher = req.getRequestDispatcher("jsp/admin.jsp");
                requestDispatcher.forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action.equals("logout")) {
            req.getSession().setAttribute(IS_LOGGED, null);
            resp.sendRedirect("/");
            logger.info("Logged out");
        } else if (action.equals("lang")) {
            req.getSession().setAttribute("language", req.getParameter("language"));
            resp.sendRedirect("/admin?page=1");
            logger.info("Language changed");
        } else {
            req.getSession().setAttribute("success", 0);
            resp.sendRedirect("/edit");
        }
    }
}
