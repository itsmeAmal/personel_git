<%-- 
    Document   : menu_admin
    Created on : Dec 24, 2016, 8:10:08 PM
    Author     : 4m4l
--%>

<%@page import="java.util.List"%>
<%@page import="org.hibernate.Criteria"%>
<%@page import="org.hibernate.Session"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript">
            window.location.hash = "no-back-button";
            window.location.hash = "Again-No-back-button";//again because google chrome don't insert first hash into history
            window.onhashchange = function () {
                window.location.hash = "no-back-button";
            }
        </script>
        <link rel="stylesheet" href="com.official.cazzendra.css.common/style.css" type="text/css" media="all">
    </head>
    <body>
        <div class="example">
            <ul id="nav">
                <li><a href="index.jsp">Home</a></li>
                <li><a href="#">Register</a>
                    <div>
                        <ul>
                            <li><a href="userAdd.jsp">User</a></li>
                        </ul>
                    </div>
                </li>
                <li><a href="#">My Cart</a>
                    <div>
                        <ul>
                            <li><a href="cartItemList.jsp">Cart Items</a></li>                           
                        </ul>
                    </div>
                </li>

                <li><a href="logout">Logout</a></li>
                <li><a href="#">Products</a>
                    <div>
                        <ul>
                            <li><a href="sessionCart.jsp">Laptops</a></li>                           
                        </ul>
                    </div>
                </li>
                <li class="pad"></li>
            </ul>
        </div>
    </body>
</html>
