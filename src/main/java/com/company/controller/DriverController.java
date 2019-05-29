package com.company.controller;


import com.company.dao.implementation.Connector;
import com.company.dao.implementation.DriverDaoImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


public class DriverController extends HttpServlet {




    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("jsp/driver.jsp");
        requestDispatcher.forward(req,resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            long id = (long)req.getSession().getAttribute("id");
            new DriverDaoImpl(new Connector()).update(id,true);
            req.getSession().setAttribute("accepted",true);
        } catch (SQLException e) {
            //TODO: logger
            e.printStackTrace();
        }

        resp.sendRedirect("/driver");
    }

}
