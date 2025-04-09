package org.lab1.servlets;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.lab1.models.Family;

import java.io.IOException;

@WebServlet(name = "AddMoneyServlet", value = "/add-money")
public class AddMoneyServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Получаем сумму из формы
        float amount = Float.parseFloat(request.getParameter("amount"));

        // Добавляем деньги в бюджет семьи
        Family family = MainServlet.getFamily();
        family.getMoney().makeMoney(amount);

        // Перенаправляем обратно на главную страницу
        response.sendRedirect(request.getContextPath() + "/");
    }
}