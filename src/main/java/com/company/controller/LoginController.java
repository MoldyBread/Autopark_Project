package com.company.controller;

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
import java.io.PrintWriter;
import java.util.Optional;

public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("jsp/login.jsp");
        requestDispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        Optional<User> opt = new AdminDaoImpl(new Connector()).findByLoginAndPassword(login, password);
        if(opt.isPresent()){
            loggingForward(req, resp, opt.get());
        }else{
            opt = new DriverDaoImpl(new Connector()).findByLoginAndPassword(login, password);
            if(opt.isPresent()) {
                loggingForward(req, resp, opt.get());

            }else {
                PrintWriter writer = resp.getWriter();
                writer.print("<h1>Not found</h1>");
            }
        }
    }

    private void loggingForward(HttpServletRequest req, HttpServletResponse resp, User loggedUser) throws ServletException, IOException {
        req.getSession().setAttribute("login", loggedUser.getLogin());
        req.getSession().setAttribute("type", loggedUser.getUserType());
        req.getRequestDispatcher("/menu").forward(req, resp);
    }
}