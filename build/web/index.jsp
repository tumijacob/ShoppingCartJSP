<%-- 
    Document   : index
    Created on : 13 Oct 2013, 6:01:04 AM
    Author     : Rendani
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include  file="inc/shoppingCart.jsp" %>
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
                        <th colspan="3">REMEDIES PRODUCTS</th>
                    </tr>
                    <tr class="hd2">
                        <th>Product Name</th><th>Product Price</th><th>Quantity</th>
                    </tr>
                </thead>
                <tbody>
                    <%= products %>
                    <tr > <th colspan="3" align ="center"><input type="submit" value="Submit" /></th> </tr>
                </tbody>
            </table>
        </form>
    </div>
    </body>
</html>
