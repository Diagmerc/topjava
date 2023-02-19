<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<header>
    <a href="<spring:url value="meals"/>"><spring:message code="app.title"/></a>
    | <a href="<spring:url value="users"/>"><spring:message code="user.title"/></a>
    | <a href="<spring:url value="${pageContext.request.contextPath}"/>"><spring:message code="app.home"/></a>
</header>