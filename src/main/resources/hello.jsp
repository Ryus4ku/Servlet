<%@ page import="java.util.List" %>
<%@ page import="ru.ryu.entity.Word" %>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta/>
        <title>JSP Application</title>
    </head>
    <body>
        <h2>Поиск слов</h2>
        <form name="search" action="${pageContext.request.contextPath}/Search" method="post">
            <input type="text" name="searchingQuery" value="" width="100" />
            <input type="submit" value="Поиск"/>
        </form>
        <br/>
        <c:set var="keys" value="${keys}"/>
        <c:forEach var="key" items="${keys}">
            <c:forEach var="object" items="${searchResult.get(key)}">
                <p><b>${key}</b> встречается в каталоге <i>"${object.getParent()}"</i> в файле <i>"${object.getFileName()}"</i> <b>${object.getCount()}</b>
                    <c:if test="${object.getCount() > 1 && object.getCount() < 5}">раза</c:if>
                    <c:if test="${object.getCount() <= 1 || object.getCount() >= 5}">раз</c:if></p>
            </c:forEach>
        </c:forEach>
    </body>
</html>