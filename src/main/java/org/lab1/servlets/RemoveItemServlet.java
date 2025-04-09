package org.lab1.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.lab1.models.Family;

import java.io.IOException;

@WebServlet(name = "RemoveItemServlet", value = "/remove-item")
public class RemoveItemServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        int userIndex = Integer.parseInt(request.getParameter("userIndex"));
        int itemIndex = Integer.parseInt(request.getParameter("itemIndex"));


        Family family = MainServlet.getFamily();
        if (userIndex >= 0 && userIndex < family.getUserList().size()) {

            float itemCost = family.getUserList().get(userIndex).getBuyList().get(itemIndex).getCost();
            family.getMoney().makeMoney(itemCost);


            family.getUserList().get(userIndex).getBuyList().remove(itemIndex);
        }


        response.sendRedirect(request.getContextPath() + "/");
    }
}
