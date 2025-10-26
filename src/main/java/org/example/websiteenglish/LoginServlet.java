package org.example.websiteenglish;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.Map;

import org.example.websiteenglish.service.UserService;
import org.example.websiteenglish.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Login", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        // Показываем форму логина
        req.getRequestDispatcher("login.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String email = req.getParameter("email");
        if (email == null || email.isEmpty()) {
            email = req.getParameter("login");
        }
        String password = req.getParameter("password");

        resp.setContentType("text/html; charset=UTF-8");

        if (email == null || email.isEmpty() || password == null) {
            req.setAttribute("error", "Введите email и пароль");
            req.getRequestDispatcher("/login.ftl").forward(req, resp);
            return;
        }

        if (userService.authenticate(email, password)) {
            var user = userService.getByEmail(email);

            HttpSession session = req.getSession();
            session.setAttribute("userEmail", email);
            session.setAttribute("userName", user.getName());
            session.setAttribute("userId", user.getId()); // ✅ добавили, чтобы работал профиль

            session.setMaxInactiveInterval(60 * 60);

            Cookie cookie = new Cookie("userEmail", email);
            cookie.setMaxAge(24 * 60 * 60);
            cookie.setPath("/");
            resp.addCookie(cookie);

            resp.sendRedirect("index"); // ✅ раньше было "index.ftl"
        } else {
            req.setAttribute("error", "Неверный email или пароль");
            req.getRequestDispatcher("/login.ftl").forward(req, resp);
        }
    }

}
