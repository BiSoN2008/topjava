<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.time.format.DateTimeFormatter" %>
<html>
<head>
    <title>Meals List</title>
    <style>
        table {
            width: 90%;             /*Ширина таблицы */
            background: grey;       /*Цвет фона таблицы*/
            color: black;           /*Цвет текста*/
            border-spacing: 1px;    /*Растоянние между ячейками*/
        }

        td, th {
            padding: 5px;           /*Поля вокруг текста*/
        }
    </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<jsp:useBean id="resultList" class="java.util.concurrent.ConcurrentHashMap" scope="request"/>
<table>
    <thead>
    <tr>
        <th>Дата</th>
        <th>Описание</th>
        <th>Калории</th>
        <th colspan=2>Действие</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${resultList}" var="entry">
        <tr style="background-color: ${entry.value.excess ? 'red' : 'greenYellow'}">
            <td align="left">${entry.value.dateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy:HH-mm"))}</td>
            <td align="left">${entry.value.description}</td>
            <td align="left">${entry.value.calories}</td>
            <td><a href="meals?action=edit&Id=<c:out value="${entry.key}"/>">Update</a> </td>
            <td><a href="meals?action=delete&Id=<c:out value="${entry.key}"/>">Delete</a> </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<form name = "form1" action="edit" method="post">
    <input type="datetime-local">
    <input type="text">
    <input type="text">
</form>
<p><a href="meals?action=insert">Добавить запись</a></p>
</body>
</html>
