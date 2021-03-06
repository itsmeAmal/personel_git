<%-- 
    Document   : productDetails
    Created on : Aug 3, 2018, 12:33:59 AM
    Author     : Amal
--%>

<%@page import="ifix.controller.stocksController"%>
<%@page import="ifix.model.User"%>
<%@page import="ifix.controller.userController"%>
<%@page import="ifix.controller.imageUploadController"%>
<%@page import="ifix.model.ImageUpload"%>
<%@page import="ifix.core.Validations"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="com.official.cazzendra.css.common/bootstrap.min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Details</title>
        <style>
            body{
                margin: 0;
                font-family: Arial;
            }
            .top-container{
                /*background-color: #999999;*/
                padding: 10px;
                text-align: center;
                height: 80px;
            }
            .header{
                padding: 10px 16px;
                background: #cccccc;
                color: #000000; 
                height: 70px;
            }
            .content{
                padding: 16px;
            }
            .sticky{
                position: fixed;
                top: 0px;
                width: 100%;
            }
            .sticky + .content{
                padding-top: 102px;
            }

            .footer{
                position: fixed;
                left: 0px;
                bottom: 0px;
                width: 100%;
                background-image: url(web.pos.ee.images/footer.PNG);
                height: 50px;
            }
        </style>
    </head>
    <body>
        <div style="position: relative; left: 0px; width: 100%; height: 30px; top: 0px; background-color: #cccccc; font-family: Arial; text-align: center; color: #333333;">               
            Working Hours : Monday - Friday : 8.30 am - 5.30 pm / Saturday : 8.30 am - 1.30 pm
        </div>
        <div class="top-container">          
            <h1>iFix Laptops.com</h1>
            <p>
                Something Different
            </p>
        </div>
        <div style="position: relative; left: 30%; top: 10%; width: 20%; height: 50px; ">
            <%
                HttpSession hs = request.getSession();
                String email = (String) hs.getAttribute("loggedIn");
                if (hs.getAttribute("loggedIn") != null) {
                    User user = userController.getuserByUserEmail(email);
            %>
            <p style="font-weight: 400;  font-size: 16px;">
                Hi  <a href="userProfile.jsp"><%= user.getUserName()%></a> 

            </p>
            <%
            } else {
            %>
            <p style="font-weight: 400;  font-size: 16px;">
                Hi  <a href="login.jsp">Guest</a>
            </p>
            <%
                }
            %>

        </div> 
        <div class="header" id="myHeader">
            <div style="position: relative; left: 20%; top: 10%; width: 150px; height: 30px; font-size: 20px;
                 color: #ffffff; font-weight: 500;"></div>
            <div style="position: relative; left: 30%; top: -52%; width: 150px; height: 30px; font-size: 20px;
                 color: #ffffff; font-weight: 500;" ><a href="productList.jsp">HOME</a></div>
            <div style="position: relative; left: 54%; top: -120%; width: 20%; height: 40px;" >
                <input type="text" class="form-control" id="search" placeholder="SEARCH" />
            </div>
        </div>
        <div>
            <%
                HttpSession ses = request.getSession();
                String ss = (String) ses.getAttribute("laptopId");
                ImageUpload imageUpload = imageUploadController.getLaptopById(ss);
            %>
            <div style="position: absolute; left: 25%; top: 30%; width: 500px; height: 500px; border: groove; align-content: center;">  
                <div style="position: relative; left: 28%; top: 28%; ">
                    <image src="uploadedImages/<%= imageUpload.getFileName()%>">
                </div>
            </div>
            <div style="position: absolute; left: 25%; top: 30%; width: 78px; height: 69px; background-image: url(web.pos.ee.images/13_off.PNG)">
            </div>
            <div style="position: absolute; left: 55%; top: 30%; width: 20%; height: 100px;"><h3><%= imageUpload.getItemDescription()%> <%= imageUpload.getModel()%> <%= imageUpload.getProcesser()%></h3></div>
            <div style="position: absolute; left: 55%; top: 37%; width: 20%; height: 100px;">
                <h4 style="color: #009900;"><%= imageUpload.getMemory()%> / <%= imageUpload.getStorage()%> / <%= imageUpload.getOs()%> / <%= imageUpload.getMemory()%> / <%= imageUpload.getGraphics()%> / <%= imageUpload.getDisplay()%></h4>
            </div>
            <div style="position: absolute; left: 55%; top: 42%; width: 300px; height: 100px;"><h4 style="color: #ff0000; font-weight: 600;">Rs <%= imageUpload.getPrice()%></h4></div>
            <div style="position: absolute; left: 55%; top: 52%; width: 150px; height: 50px; ">
                <%
                    String model = imageUpload.getModel();
                    int qty = stocksController.getCurrentStockByModelNo(model);
                    if (qty != 0) {
                %>
                <form action="addToDatabaseCart" method="post">
                    <input class="btn btn-success" type="submit" value="Add to Cart" style="width: 100px; background-color: #990099;"/>
                    <input type="hidden" id="laptopId" name="laptopId" value="<%=imageUpload.getId()%>">    
                </form>
                <%
                    }
                %>
            </div>
            <div style="position: absolute; left: 62%; top: 52%; width: 200px; height: 50px; ">
                <!--                <form action="cashOnDelivery" method="post">
                                    <input class="btn btn-primary" type="submit" value="Cash On Delivery" style="width: 130px; background-color: #990099;"/>
                                    <input type="hidden" id="laptopId" name="laptopId" value="<%=imageUpload.getId()%>"> 
                                </form>-->
            </div>
            <!--<div style="position: absolute; left: 63%; top: 52%; width: 145px; height: 48px; background-image: url(web.pos.ee.images/buy_now.PNG)"></div>-->
            <div style="position: absolute; left: 55%; top: 57%; width: 400px; height: 150px; background-image: url(web.pos.ee.images/delivery_info.PNG)" ></div>        
        </div>
        <script type="text/javascript">
            window.onscroll = function () {
                myFunction()
            };
            var header = document.getElementById("myHeader");
            var sticky = header.offsetTop;
            function myFunction() {
                if (window.pageYOffset > sticky) {
                    header.classList.add("sticky");
                } else {
                    header.classList.remove("sticky");
                }
            }
        </script>
        <div class="footer"></div>

    </body>

</html>
