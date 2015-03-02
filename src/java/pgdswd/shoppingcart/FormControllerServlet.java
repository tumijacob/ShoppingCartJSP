/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pgdswd.shoppingcart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.text.DecimalFormat;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Tumi
 */
public class FormControllerServlet extends HttpServlet {

    Connection connection;
    ConnectionPool pool;
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        DecimalFormat formatter = new DecimalFormat();
        formatter.setMaximumFractionDigits(2);
        formatter.setMinimumFractionDigits(2);
        
        Enumeration<String> parameterNames = request.getParameterNames();
        ArrayList<ShoppingCart> shoppingCart = new ArrayList<ShoppingCart>();
        ResultSet product;
        double totalPrice = 0;
        //check if the the user is ready to submit final form 
        boolean processForm = false;
        
        //extract parameter names of selected products
        while(parameterNames.hasMoreElements()){
            String paramname = (String)parameterNames.nextElement();
            if(paramname.equals("viewForm")){
                processForm = true;
            }
            String value = request.getParameter(paramname);
            if(value.matches("[0-9]{1,}")){
                int quantity = Integer.parseInt(value);
                int productId = Integer.parseInt(paramname.substring(7));
                if(quantity > 0){
                    try{
                        product = connect(productId);
                        if(product.next()){
                            shoppingCart.add(new ShoppingCart(product.getString(
                                    "ProductName"),productId, product.getDouble("ProductPrice"),
                                    quantity,quantity*product.getDouble("ProductPrice")));
                            totalPrice += quantity*product.getDouble("ProductPrice");
                        }
                    }catch(Exception e){
                        pool.releaseConnection(connection);
                        throw new ServletException(e);
                    }
                }
            }
        }
        
        if(!shoppingCart.isEmpty()){
            pool.releaseConnection(connection);
            String displayShoppingcart = "";
            String msg = "";
            for(int i = 0; i < shoppingCart.size(); i++){
                String entry;
                
                if(!processForm){
                    entry = "<input type = 'text' id = "
                        + "'product"+shoppingCart.get(i).getProductId()+"' "
                        + "name = 'product"+shoppingCart.get(i).getProductId()+"'"
                        + " value = '" + shoppingCart.get(i).getQuantity() + 
                        "' size = '1'/>";
                    
                }else{
                    entry = String.valueOf(shoppingCart.get(i).getQuantity());
                    msg = "hidden = 'true' ";
                }
                
                displayShoppingcart += "<tr><td>" + shoppingCart.get(i).getProductName()
                        +"</td><td>" + entry + "</td><td>R" + shoppingCart.get(i).getProductPrice() +
                        "0</td><td>R" + shoppingCart.get(i).getItemTotal() +"0</td></tr>";
            }
            displayShoppingcart += "<tr ><td colspan = '3' align = 'right'>"
                    + "<b>Order Total:</b></td><td align = 'left'><b>R"
                    + formatter.format(totalPrice)+"</b></td></tr>";
            
            HttpSession session = request.getSession();
            session.setAttribute("shoppingCart", shoppingCart);
            request.setAttribute("table", displayShoppingcart);
            request.setAttribute("processForm",msg);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/viewCart.jsp");
            dispatcher.forward(request, response);
        }else{
            response.sendRedirect("index.jsp");
        }
    }
    

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Handle database connections, extract selected products and "
                + " send them back to the user in the form of table.";
    }
    
    //connect to the database and retrive a selected row
    public ResultSet connect(int id) throws SQLException{
       
        pool = ConnectionPool.getInstance();
        connection = pool.getConnection();
        PreparedStatement selectProduct = connection.prepareStatement(
            "SELECT ProductName, ProductPrice FROM Product WHERE ProductID = ?");
        selectProduct.setInt(1, id);
        return selectProduct.executeQuery();
    }
}
