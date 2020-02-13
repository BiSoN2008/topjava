<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="dd.MM.yyyy" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.time.format.DateTimeFormatter" %>
<html>
<head>
    <title>Meals List</title>
    <style>
        table {
            width: 50%;             /*Ширина таблицы */
            background: green;       /*Цвет фона таблицы*/
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
<br>
<h2>Редактирование данных</h2>
<form name = "form1" action="${pageContext.request.contextPath}/meals" method="post">
    <input type="datetime-local" value="<c:out value="${resultList.value.dateTime}"/> ">
    <input type="text" value="<c:out value="${resultList.value.description}"/> ">
    <input type="text" value="<c:out value="${resultList.value.calories}" />">
    <input type="submit" name="edit" value="Сохранить">
</form>

<h2>Добавить данные</h2>
<table>
<thead>
<tr>
    <th>Введите дату,время</th>
    <th>Введите описание</th>
    <th>Введите калории</th>
    <th></th>
</tr>
</thead>
    <tbody>
    <tr style="background-color: aliceblue">
        <form name="form2"  method="post">
        <td><input type="datetime-local" name="dateTime" value="" size="100%"></td>
        <td><input type="text" name="description" value=""></td>
        <td><input type="text" name="calories" value=""></td>
        <td><input type="submit" name="insert" value="Add"></td>
    </form>
    </tr>
    </tbody>
</table>

<p><a href="meals?action=insert">Добавить запись</a></p>
</body>
</html>
