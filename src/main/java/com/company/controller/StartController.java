package com.company.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StartController extends HttpServlet {

    private static final String IS_LOGGED = "isLogged";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (null == req.getSession().getAttribute("language")) {
            req.getSession().setAttribute("language", "en");
        }
        if (null == req.getSession().getAttribute(IS_LOGGED)) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("jsp/index.jsp");
            requestDispatcher.forward(req, resp);
        } else if ((int) req.getSession().getAttribute(IS_LOGGED) == 1) {
            resp.sendRedirect("/admin?page=1");
        } else if ((int) req.getSession().getAttribute(IS_LOGGED) == 2) {
            resp.sendRedirect("/driver");
        }
    }
}
