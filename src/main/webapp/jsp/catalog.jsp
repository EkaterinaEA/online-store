<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

</head>
<body>
<h1>Catalog</h1><br />
<c:forEach var="itemMap" items="${requestScope.itemMap}">
    <ul>
        <li>Product name: <c:out value="${itemMap.name}"/></li>
        <li>Price: <c:out value="${itemMap.price}"/></li>
        <li>Product Url: <c:out value="${itemMap.url}"/></li>
        <li>Image Url: <c:out value="${itemMap.imageUrl}"/></li>
        <li>Product id: <c:out value="${itemMap.itemId}"/></li>

    </ul>
    <hr />
</c:forEach>

</body>
</html>
