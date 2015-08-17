<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main</title>

    <link href="<c:url value="/static/css/main-style.css"/>" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
    <script src="<c:url value="/static/js/js.cookie-2.0.3.min.js"/>"></script>
    <script src="<c:url value="/static/js/uuid.js"/>"></script>


</head>
<body>

<div class="container">

    <div class="page-header">
        <h1>CSRF Demo</h1>
    </div>

    <p class="lead">To send request to the server, please follow
        <a href="<c:url value="/app/csrf"/>">the link</a>. Or we can also send a request using JavaScript.
    </p>


    <%-- Simple form --%>

    <form id='form' action='<c:url value="/app/csrf"/>' method='POST'>

        <input id='do-not-share-your-token-with-anyone' type='hidden' name='MY-CSRF-TOKEN' value=''>
        <button class="btn btn-success btn-lg" onclick="sendRequest(); return false;">Send</button>

    </form>

</div>

<script>
    function sendRequest() {
        var uniqueId = uuid.v4();
        Cookies.set('MY-CSRF-TOKEN', uniqueId);

        $('#do-not-share-your-token-with-anyone').attr('value', uniqueId);
        $('#form').submit();
    }
</script>

</body>
</html>