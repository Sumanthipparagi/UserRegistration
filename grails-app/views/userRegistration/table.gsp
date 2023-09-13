<%--
  Created by IntelliJ IDEA.
  User: Sumant
  Date: 05/09/2023
  Time: 12:15
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.6/css/dataTables.bootstrap5.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/css/bootstrap.min.csss">
    <asset:javascript src="/pages/usertable.js"/>
    <style>

    /* Basic styling for the table */
    table {
        border-collapse: collapse;
        width: 100%;
        border: 1px solid #ccc;
    }

    th, td {
        font-family: 'poppins', sans-serif;
        border: 1px solid #ccc;
        padding: 8px;
        text-align: left;
    }

    th {
        background-color: #f2f2f2;
        font-size: 18px;
    }

    /* Alternate row coloring */
    tr:nth-child(even) {
        background-color: #f2f2f2;
    }

    h2{
        font-size: 30px;
        margin-left: 650px;
    }
    </style>
    <title>Simple Table Example</title>
</head>
<body>

<h2>Registration Details</h2>
<table id="userTable">
    <thead>
    <tr>
        <th>Name</th>
        <th>LoginId</th>
        <!-- <th>Password</th> -->
        <th>Address</th>
        <th>MobileNo</th>
        <th>Designation</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    </thead>
    <tbody id="userTbBody">

    </tbody>
</table>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
</body>
</html>


</body>
</html>

