<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<c:if test="${not empty error}">
    <p> ${error} </p>
</c:if>

<spring:message code="eurobusiness.actions.pay.yourAccountBalance"/> : ${player.accountAmount}

<form:form modelAttribute="payForm" method="post">
<c:forEach items="${players}" var="player">
  <form:radiobutton path="destination" value="${player.id}"/> ${player.name} <spring:message code="eurobusiness.actions.pay.accountBalance"/> : ${player.accountAmount}<br>
</c:forEach>
  <form:radiobutton path="destination" value="bank"/><spring:message code="eurobusiness.actions.pay.bank"/> <br>
  <form:input type="text" path="value"/><br><c:if test="${pageContext.request.method=='POST'}"><form:errors path="value" /></c:if>
  <input type="submit" value=<spring:message code="eurobusiness.actions.pay"/> />
</form:form>

<p><a href="/game/${player.id}"> <spring:message code="eurobusiness.actions.back"/> </a></p>