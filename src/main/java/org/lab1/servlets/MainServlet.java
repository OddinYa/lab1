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

@WebServlet(name = "MainServlet", value = "")
public class MainServlet extends HttpServlet {

    private static Family family;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        family = initFamily();

        int people = family.getUserList().size();

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Рассходы семьи</title>");
            out.println("</head>");
            out.println("<body>");

            out.println("<h1>Таблица Расходов</h1>");

            out.println("<table>");
            out.println("<tbody>");

         // for (int c = 1; c <= people; c++) {
         //     out.println("<tr>");
         //     for (int r = 1; r <= rows; r++) {
         //         out.println("<td>");
         //         out.println(DataModel.getValue(r, c));
         //         out.println("</td>");
         //     }
         //     out.println("</tr>");
         // }

            out.println("</tbody>");
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
}
