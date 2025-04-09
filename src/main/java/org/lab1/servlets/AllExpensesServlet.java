package org.lab1.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.lab1.models.Buy;
import org.lab1.models.Family;
import org.lab1.models.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "AllExpensesServlet", value = "/all-expenses")
public class AllExpensesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Family family = MainServlet.getFamily();
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Все расходы семьи</title>");

            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Все расходы семьи</h1>");
            out.println("<a href=\"" + request.getContextPath() + "/\">На главную</a>");

            out.println("<table>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>Пользователь</th>");
            out.println("<th>Наименование</th>");
            out.println("<th>Цена</th>");
            out.println("<th>Дата</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");

            float totalExpenses = 0;
            List<User> users = family.getUserList();

            for (User user : users) {
                float userTotal = 0;
                for (Buy buy : user.getBuyList()) {
                    out.println("<tr>");
                    out.println("<td>" + user.getName() + "</td>");
                    out.println("<td>" + buy.getName() + "</td>");
                    out.println("<td>" + buy.getCost() + "</td>");
                    out.println("<td>" + buy.getDate() + "</td>");
                    out.println("</tr>");

                    userTotal += buy.getCost();
                }

                out.println("<tr class=\"total\">");
                out.println("<td colspan=\"2\">Итого " + user.getName() + ":</td>");
                out.println("<td>" + userTotal + "</td>");
                out.println("<td></td>");
                out.println("</tr>");

                totalExpenses += userTotal;
            }

            out.println("<tr class=\"total\">");
            out.println("<td colspan=\"2\">Общие расходы семьи:</td>");
            out.println("<td>" + totalExpenses + "</td>");
            out.println("<td></td>");
            out.println("</tr>");

            out.println("</tbody>");
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
