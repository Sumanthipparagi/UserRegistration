<%--
  Created by IntelliJ IDEA.
  User: Sumant
  Date: 05/09/2023
  Time: 12:08
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <asset:stylesheet src="styles.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <link
            href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css"
            rel="stylesheet" />
    <script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
    <asset:javascript src="/pages/book.js"/>

    <title>Notice Board</title>
</head>
<body>
<div class="notice-board">
    <h1>Book Entry</h1>
    <form>
        <label for="book-name">Book Name:</label>
        <input type="text" id="book-name" name="book-name" required>

        <label for="author">Author:</label>
        <input type="text" id="author" name="author" required>

        <label for="genre">Genre:</label>
        <select id="genre" name="genre">

        </select>

        <label for="book-price">Book Price:</label>
        <input type="number" id="book-price" name="book-price" required>

        <a href="http://127.0.0.1:5500/bookTable.html"><button type="button">Edit</button></a><br>
        <button type="submit" id="submit">Submit</button>
    </form>
</div>
</body>
</html>



