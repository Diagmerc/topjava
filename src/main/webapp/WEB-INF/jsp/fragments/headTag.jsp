<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring:message code="app.title"/></title>
    <base href="${pageContext.request.contextPath}/"/>

    <link rel="stylesheet" href="resources/css/style.css?v=2">
    <link rel="stylesheet" href="webjars/bootstrap/4.6.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="webjars/noty/3.1.4/demo/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="webjars/datatables/1.13.1/css/dataTables.bootstrap4.min.css">
    <link rel="stylesheet" href="webjars/noty/3.1.4/lib/noty.css"/>
    <link rel="shortcut icon" href="resources/images/icon-meal.png">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="/resources/demos/style.css">

    <%--http://stackoverflow.com/a/24070373/548473--%>
    <script src="webjars/jquery/3.6.1/jquery.min.js" defer></script>
    <script src="webjars/bootstrap/4.6.2/js/bootstrap.min.js" defer></script>
    <script src="webjars/datatables/1.13.1/js/jquery.dataTables.min.js" defer></script>
    <script src="webjars/datatables/1.13.1/js/dataTables.bootstrap4.min.js" defer></script>
    <script src="webjars/noty/3.1.4/lib/noty.min.js" defer></script>
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<%--    <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>--%>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.11/jquery-ui.min.js"></script>
    <script>
        $( function() {
            $( "#datepicker" ).datepicker();
        } );
    </script>
</head>