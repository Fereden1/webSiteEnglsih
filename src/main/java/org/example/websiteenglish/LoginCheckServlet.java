package org.example.websiteenglish;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.websiteenglish.service.UserService;
import org.example.websiteenglish.service.impl.UserServiceImpl;

import java.io.IOException;

@WebServlet(urlPatterns = "/check/email")
public class LoginCheckServlet extends HttpServlet {

    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email"); // получаем email из запроса

        resp.setContentType("text/plain");
        if (email == null || email.isBlank()) {
            resp.getWriter().write("Email не может быть пустым");
            return;
        }

        if (userService.emailExists(email)) {
            resp.getWriter().write("Этот email уже занят! Пожалуйста, выберите другой.");
        } else {
            resp.getWriter().write(""); // пустой ответ = email свободен
        }
    }
}
