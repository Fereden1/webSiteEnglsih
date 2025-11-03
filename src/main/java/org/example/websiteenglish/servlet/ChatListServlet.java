package org.example.websiteenglish.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.websiteenglish.utils.DatabaseConnectionUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/chat/list")
public class ChatListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("application/json; charset=UTF-8");

        try (Connection conn = DatabaseConnectionUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "SELECT c.text, c.created_at, u.name " +
                             "FROM comments c JOIN users u ON c.user_id = u.id " +
                             "ORDER BY c.created_at DESC LIMIT 20"
             )) {

            List<String> jsonMessages = new ArrayList<>();
            while (rs.next()) {
                String name = rs.getString("name");
                String text = rs.getString("text").replace("\"", "\\\"");
                String time = rs.getTimestamp("created_at").toString();
                jsonMessages.add(String.format("{\"name\":\"%s\",\"text\":\"%s\",\"time\":\"%s\"}", name, text, time));
            }

            PrintWriter out = resp.getWriter();
            out.print("[" + String.join(",", jsonMessages) + "]");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
