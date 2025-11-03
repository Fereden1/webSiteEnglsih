package org.example.websiteenglish.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.websiteenglish.utils.DatabaseConnectionUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/chat/send")
public class ChatSendServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Login required");
            return;
        }

        int userId = (int) session.getAttribute("userId");
        String text = req.getParameter("text");

        if (text == null || text.trim().isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Message cannot be empty");
            return;
        }

        try (Connection conn = DatabaseConnectionUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO comments(user_id, text) VALUES(?, ?)");
            stmt.setInt(1, userId);
            stmt.setString(2, text.trim());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
