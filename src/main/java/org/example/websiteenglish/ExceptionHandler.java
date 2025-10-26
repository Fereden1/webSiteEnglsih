package org.example.websiteenglish;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/handle-error")
public class ExceptionHandler extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handle(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handle(req, resp);
    }

    private void handle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Получаем атрибуты ошибки, установленные контейнером сервлетов
        Throwable throwable = (Throwable) req.getAttribute("jakarta.servlet.error.exception");
        Integer statusCode = (Integer) req.getAttribute("jakarta.servlet.error.status_code");
        String requestUri = (String) req.getAttribute("jakarta.servlet.error.request_uri");

        // Если URI отсутствует, ставим пустую строку
        if (requestUri == null) requestUri = "";

        // Передаем данные в шаблон
        req.setAttribute("statusCode", statusCode);
        req.setAttribute("uri", requestUri);

        if (statusCode != null && statusCode == 500 && throwable != null) {
            req.setAttribute("message", throwable.getMessage());
        } else {
            req.setAttribute("message", "Произошла ошибка на сервере");
        }

        // Перенаправляем на шаблон ошибок
        req.getRequestDispatcher("/exception.ftl").forward(req, resp);
    }
}
