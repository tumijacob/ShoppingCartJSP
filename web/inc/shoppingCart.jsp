<%-- 
    Document   : shoppingCart
    Created on : 13 Oct 2013, 8:46:55 AM
    Author     : Tumi
--%>
<%@page import="java.sql.*, pgdswd.shoppingcart.ConnectionPool" %>

<%
    //database connections
    ConnectionPool pool = ConnectionPool.getInstance();
    Connection connection = pool.getConnection();
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery("SELECT ProductID, ProductName,"
            + " ProductPrice FROM Product");
    
    
        String products = "";
        //print all products name and price in the table
        if(resultSet.next()){
            while(resultSet.next()){
                products += "<tr><td>" + resultSet.getString("ProductName")+
                        "</td><td>R" + resultSet.getDouble("ProductPrice")+"0</td>"
                        + "<td><input type = 'text' id = 'product"+ 
                        resultSet.getInt("ProductID")+ "' name = 'product" + 
                        resultSet.getInt("ProductID")+ "' size = '1' ></td></tr>"; 
            }
        }else{
            //if the are no products in the table, inform the user
            products += "No products";
        }
        pool.releaseConnection(connection);
%>
