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
import java.io.PrintWriter;
import java.util.Optional;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("jsp/login.jsp");
        requestDispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        Optional<User> opt = new AdminDaoImpl("admins",new Connector()).findByLoginAndPassword(login, password);

        PrintWriter writer = resp.getWriter();

        User loggedUser;
        if(opt.isPresent()){
            loggedUser= opt.get();

            req.setAttribute("user", loggedUser);

            getServletContext().getRequestDispatcher("jsp/main.jsp").forward(req, resp);


        }else{
            opt = new DriverDaoImpl("drivers",new Connector()).findByLoginAndPassword(login, password);
            if(opt.isPresent()) {
                loggedUser = opt.get();
                req.setAttribute("login", loggedUser);
//                req.setAttribute("type", loggedUser.getUserType().toString());

                getServletContext().getRequestDispatcher("jsp/main.jsp").forward(req, resp);
            }else {
                writer.print("<h1>Not found</h1>");
            }
        }
    }


}