<%-- 
    Document   : viewCart
    Created on : 13 Oct 2013, 10:23:03 AM
    Author     : Rendani
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/shoppingCart.css">
        <%@include  file="inc/validateInputs.jsp" %>
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        
        <div>
        <form action="FormController" method="POST" onsubmit="return subm();">
                        <table> 
                <thead>
                    <tr class="hd1">
                        <th colspan="4">Shopping Cart Items</th>
                    </tr>
                    <tr class="hd2">
                        <th>Product Name</th><th>Quantity</th><th>Product Price</th><th>Item Total</th>
                    </tr>
                </thead>
                <tbody>
                    <%= request.getAttribute("table") %>
                    <tr hidden="true"><td><input type="text" name="viewForm"/></td></tr>
                    <tr <%= request.getAttribute("processForm") %> > <th colspan="4"><input type="submit" value="Submit" /></th></tr>
                </tbody>
            </table>
        </form>
        </div>
    </body>
</html>
