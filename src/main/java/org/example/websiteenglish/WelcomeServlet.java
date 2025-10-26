package org.example.websiteenglish;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/welcome")
public class WelcomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        resp.setContentType("text/html; charset=UTF-8");

        if (session != null && session.getAttribute("userEmail") != null) {
            String email = (String) session.getAttribute("userEmail");
            String name = (String) session.getAttribute("userName");

            resp.getWriter().println("<h1>Добро пожаловать, " + (name != null ? name : email) + "!</h1>");
            resp.getWriter().println("<p>Email: " + email + "</p>");
            resp.getWriter().println("<a href='logout'>Выйти</a>");
        } else {
            resp.sendRedirect("login.ftl");
        }
    }
}