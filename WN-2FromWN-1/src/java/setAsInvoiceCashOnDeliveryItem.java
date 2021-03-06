/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ifix.controller.CartReferenceController;
import ifix.core.MethodStatus;
import ifix.core.Validations;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Amal
 */
@WebServlet(urlPatterns = {"/setAsInvoiceCashOnDeliveryItem"})
public class setAsInvoiceCashOnDeliveryItem extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
            MethodStatus status = null;
            String deliveryStatus = request.getParameter("dlveryStatus");
            String date = request.getParameter("date");
            String itemId = request.getParameter("itemId");
            String userId = request.getParameter("userId");

            int deliveStatus = Validations.getIntOrZeroFromString(deliveryStatus);
            int itemIdInt = Validations.getIntOrZeroFromString(itemId);
            int userIdInt = Validations.getIntOrZeroFromString(userId);

            if (deliveStatus == 0) {
                status = CartReferenceController.updateDeliveryStatus(1, itemIdInt, userIdInt);
            } else {
                status = CartReferenceController.updateDeliveryStatus(0, itemIdInt, userIdInt);
            }

            if (status == MethodStatus.SUCCESS) {
                response.sendRedirect("invoicedItems.jsp");
            }

        } catch (SQLException ex) {
            Logger.getLogger(setAsInvoiceCashOnDeliveryItem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
