<%--
  Created by IntelliJ IDEA.
  User: Sumant
  Date: 05/09/2023
  Time: 14:46
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <asset:stylesheet src="login.css"/>
    <script src="https://kit.fontawesome.com/82796aef64.js" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

</head>
<body>
<div class="container">
    <div class="form-box">
        <h1>Login Page </h1>
        <form id="dataForm">
            <div class="input-group">
                <div class="input-field">
                    <i class="fa-solid fa-user"></i>
                    <input type="text" id="LoginId" placeholder="loginId">
                </div>

                <div class="input-field">
                    <i class="fa-solid fa-envelope"></i>
                    <input type="text" id="password" placeholder="Password">
                </div>

                <div class="btn-field">
                    <button id="save" type="button">login</button></a>
                </div>
            </div>
        </form>
    </div>
</div>
<asset:javascript src="/pages/login.js"/>
</body>
</html>

