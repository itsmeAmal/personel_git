/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifix.sessionCart;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author AMA Wickramarathna
 */
@WebServlet(name = "addProduct", urlPatterns = {"/addProduct"})
public class addProduct extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String pid, pname, qty, up;

            pid = request.getParameter("pid");
            pname = request.getParameter("pname");
            qty = request.getParameter("qty");
            up = request.getParameter("up");

            out.print(pid);
            out.print(qty);

            CartItem cit = new CartItem();
            cit.setPid(Integer.parseInt(pid));
            cit.setPname(pname);
            cit.setQty(Integer.parseInt(qty));
            cit.setUprice(Double.parseDouble(up));

            HttpSession hs = request.getSession();
            cart c = null;
            if (hs.getAttribute("myCart") == null) {
                c = new cart();

            } else {
                c = (cart) hs.getAttribute("myCart");
            }
            boolean status = c.addProductToCart(cit);
            hs.setAttribute("myCart", c);
            if (status) {
//                response.sendRedirect("laptopModelAll.jsp");
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Added to cart');");
                out.println("location='laptopModelAll.jsp';");
                out.println("</script>");
            }

        }
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
        processRequest(request, response);
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
