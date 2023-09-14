<%--
  Created by IntelliJ IDEA.
  User: light
  Date: 14/09/2023
  Time: 17:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Choisissez votre humeur</title>
</head>
<body>
<h1>Comment vous sentez-vous aujourd'hui ?</h1>

<form method="post" action="${pageContext.request.contextPath}/setMood">
    <select name="mood">
        <c:forEach items="${moods}" var="mood">
            <option value="${mood}">${mood}</option>
        </c:forEach>
    </select>
    <button type="submit">Soumettre</button>
</form>
</body>
</html>
