package org.example.websiteenglish;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.websiteenglish.dao.impl.UserDaoImpl;
import org.example.websiteenglish.entity.User;

import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    private final UserDaoImpl userDao = new UserDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            resp.sendRedirect("login");
            return;
        }

        int userId = (int) session.getAttribute("userId");
        User user = userDao.getById(userId);

        req.setAttribute("user", user);
        req.getRequestDispatcher("/profile.ftl").forward(req, resp); // ✅ вместо JSP
    }
}
