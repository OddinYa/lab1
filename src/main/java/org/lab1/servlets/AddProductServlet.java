package org.lab1.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.lab1.models.Buy;
import org.lab1.models.Family;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "AddProductServlet", value = "/add-product")
public class AddProductServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        int userIndex = Integer.parseInt(request.getParameter("userIndex"));
        String productName = request.getParameter("productName");
        float productCost = Float.parseFloat(request.getParameter("productCost"));


        Family family = MainServlet.getFamily();


        if (family.getMoney().getCash() >= productCost) {

            Buy newBuy = new Buy(productCost, productName, LocalDate.now());


            family.getUserList().get(userIndex).spendMoney(newBuy);

        }


        response.sendRedirect(request.getContextPath() + "/");
    }
}
