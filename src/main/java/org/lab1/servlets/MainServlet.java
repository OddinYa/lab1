package org.lab1.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.lab1.models.Buy;
import org.lab1.models.Family;
import org.lab1.models.Money;
import org.lab1.models.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@WebServlet(name = "MainServlet", value = "")
public class MainServlet extends HttpServlet {

    private static Family family;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if(family == null) {
            family = initFamily();
        }
        int people = family.getUserList().size();

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Расходы семьи</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h2>Остаток бюджета: "+family.getMoney().getCash()+"</h2>");

            // Форма для добавления денег
            out.println("<h3>Добавить деньги в бюджет:</h3>");
            out.println("<form action=\"add-money\" method=\"post\">");
            out.println("Сумма: <input type=\"number\" step=\"0.01\" name=\"amount\" required>");
            out.println("<input type=\"submit\" value=\"Добавить\">");
            out.println("</form>");


            out.println("<h3>Добавить продукт:</h3>");
            out.println("<form action=\"add-product\" method=\"post\">");
            out.println("Пользователь: <select name=\"userIndex\">");
            for (int i = 0; i < family.getUserList().size(); i++) {
                out.println("<option value=\"" + i + "\">" + family.getUserList().get(i).getName() + "</option>");
            }
            out.println("</select><br>");
            out.println("Название: <input type=\"text\" name=\"productName\" required><br>");
            out.println("Цена: <input type=\"number\" step=\"0.01\" name=\"productCost\" required><br>");
            out.println("<input type=\"submit\" value=\"Добавить продукт\">");
            out.println("</form>");

            out.println("<h1>Таблица Расходов</h1>");

            List<User> userList = family.getUserList();
            for (int i = 0; i < userList.size(); i++) {
                User user = userList.get(i);
                out.println("<h2>"+ user.getName() +"</h2>");
                out.println("<table border=\"1\">");
                out.println("<thead>");
                out.println("<tr>");
                out.println("<th>Цена</th>");
                out.println("<th>Наименование</th>");
                out.println("<th>Дата</th>");
                out.println("<th>Действие</th>");
                out.println("</tr>");
                out.println("</thead>");
                out.println("<tbody>");

                for (int j = 0; j < user.getBuyList().size(); j++){
                    Buy buy = user.getBuyList().get(j);
                    out.println("<tr>");
                    out.println("<td>" + buy.getCost() + "</td>");
                    out.println("<td>" + buy.getName() + "</td>");
                    out.println("<td>" + buy.getDate() + "</td>");
                    out.println("<td>");
                    out.println("<form action=\"remove-item\" method=\"post\">");
                    out.println("<input type=\"hidden\" name=\"userIndex\" value=\"" + i + "\">");
                    out.println("<input type=\"hidden\" name=\"itemIndex\" value=\"" + j + "\">");
                    out.println("<input type=\"submit\" value=\"Удалить\">");
                    out.println("</form>");
                    out.println("</td>");
                    out.println("</tr>");
                }

                out.println("</tbody>");
                out.println("</table>");
            }

            out.println("<p><a href=\"all-expenses\">Просмотреть все расходы</a></p>");
            out.println("</body>");
            out.println("</html>");

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }



    private static Family initFamily(){

        Buy item1 = new Buy(230.3f,"Сыр", LocalDate.now());
        Buy item2 = new Buy(300.5f,"Колбаса",LocalDate.now());

        Money money = new Money(1000f);

        User user1 = new User(money,"Сергей");
        user1.spendMoney(item2);
        user1.spendMoney(item1);

        Family result = new Family(money);
        result.addInFamily(user1);

        return result;
    }

    public static Family getFamily() {
        if(family == null) {
            family = initFamily();
        }
        return family;
    }
}
