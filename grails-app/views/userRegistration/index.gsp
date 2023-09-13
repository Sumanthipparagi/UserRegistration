<%--
  Created by IntelliJ IDEA.
  User: Sumant
  Date: 05/09/2023
  Time: 12:14
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <asset:stylesheet src="userstyle.css"/>
    <script src="https://kit.fontawesome.com/82796aef64.js" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <link
            href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css"
            rel="stylesheet" />
    <script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
    <asset:javascript src="/pages/userregistration.js"/>
</head>
<body>
<div class="container">
    <div class="form-box">
        <h1>User Registration </h1>
        <form id="dataForm">
            <div class="input-group">
                <div class="input-field">
                    <i class="fa-solid fa-user"></i>
                    <input type="text" id="Name" placeholder="Name">
                </div>

                <div class="input-field">
                    <i class="fa-solid fa-envelope"></i>
                    <input type="text" id="Loginid" placeholder="Login id">
                </div>

                <div class="input-field">
                    <i class="fa-solid fa-lock"></i>
                    <input type="password" id="Password" placeholder="Password">
                </div>
                <div class="input-field">
                    <i class="fa-solid fa-location-dot"></i>
                    <input type="text" id="Address" placeholder="address">
                </div>
                <div class="input-field">
                    <i class="fa-solid fa-mobile"></i>
                    <input type="tel" id="Mobileno" placeholder="Mobile No.">
                </div>
                <div class="input-field" >
                    <select id="designation" name="designation">

                    </select>
                </div>

                <p>Lost password <a href="#">Click here!</a></p>
            </div>

            <div class="btn-field">
                <a href="/userregistration/viewtable"><button type="button">Edit</button></a>
                <button type="button" id="save">Save</button>

            </div>
        </form>


    </div>

</div>

</body>
</html>