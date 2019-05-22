package com.company;

import com.company.dao.implementation.AdminDaoImpl;
import com.company.dao.implementation.Connector;
import com.company.dao.implementation.DriverDaoImpl;
import com.company.entity.users.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class MainServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("jsp/main.jsp");
        requestDispatcher.forward(req,resp);
    }
}
