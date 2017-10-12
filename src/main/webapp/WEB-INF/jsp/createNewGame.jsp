<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><spring:message code="eurobusiness.menu.create.pagetitle" /></title>
	</head>
	<body>
        <form:form modelAttribute="playerList" method="POST">
            <form:input type="text" path="player1Name" />
            <form:input type="text" path="player2Name" />
            <form:input type="text" path="player3Name" />
            <form:input type="text" path="player4Name" />
            <input type="submit" value=<spring:message code="eurobusiness.menu.create.add"/> />
        </form:form>
	</body>
</html>