/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ifix.controller.CartReferenceController;
import ifix.controller.imageUploadController;
import ifix.controller.userController;
import ifix.core.MethodStatus;
import ifix.model.ImageUpload;
import ifix.model.User;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Amal
 */
@WebServlet(urlPatterns = {"/addToDatabaseCart"})
public class addToDatabaseCart extends HttpServlet {

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
            PrintWriter out = response.getWriter();
            String lapId = request.getParameter("laptopId");
            ImageUpload imageUpload = imageUploadController.getLaptopById(lapId);
            HttpSession hs = request.getSession();
            String email = (String) hs.getAttribute("loggedIn");
            String userSessionId = "";
            if (hs.getAttribute("loggedIn") != null) {
                User user = userController.getuserByUserEmail(email);
                userSessionId = Integer.toString(user.getUserId());
            } else {
                userSessionId = request.getSession().getId();
            }
            MethodStatus status = null;
            status = CartReferenceController.addcartReference(lapId, userSessionId,
                    imageUpload.getPrice(), imageUpload.getItemDescription() + "-" + imageUpload.getModel());
            if (status == MethodStatus.SUCCESS) {
//                response.sendRedirect("productDetails.jsp");
                // response.sendRedirect("productList.jsp");
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Added to cart');");
                out.println("location='productList.jsp';");
                out.println("</script>");

            }
        } catch (SQLException ex) {
            Logger.getLogger(addToDatabaseCart.class.getName()).log(Level.SEVERE, null, ex);
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
