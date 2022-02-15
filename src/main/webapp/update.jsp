<%--
  Created by IntelliJ IDEA.
  User: vuong
  Date: 2/15/2022
  Time: 10:28 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update</title>
    <link rel="stylesheet" href="update.css">
</head>
<body>
<form action="/ProductServlet?action=updateProduct&id=${product.id}" method="post">
    <table>
        <tr>
            <td>Name</td>
            <td><input type="text" name="name" <c:out value='${product.name}' />></td>
        </tr>
        <tr>
            <td>Price</td>
            <td><input type="text" name="price" <c:out value='${product.price}' />></td>
        </tr>
        <tr>
            <td>Quantity</td>
            <td><input type="text" name="quantity" <c:out value='${product.quantity}' />></td>
        </tr>
        <tr>
            <td>Color</td>
            <td><input type="text" name="color" <c:out value='${product.color}' />></td>
        </tr>
        <tr>
            <td>Description</td>
            <td><input type="text" name="description" <c:out value='${product.description}' />></td>
        </tr>
        <tr>
            <td>Category</td>
            <td><input type="text" name="category" <c:out value='${product.category}' />></td>
        </tr>
        <tr>
            <td><button type="submit">Update</button></td>
            <td></td>
        </tr>
    </table>
</form>
</body>
</html>
