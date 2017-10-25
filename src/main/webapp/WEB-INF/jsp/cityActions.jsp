<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<c:if test="${not empty error}">
    <p> ${error} </p>
</c:if>
<c:choose>
    <c:when test="${city.quantityOfProperty < 4}">
        <a href="/game-${game}/${player}/${city.id}/build"><spring:message code="eurobusiness.actions.cityActions.build"/></a>
    </c:when>
    <c:otherwise>
        <spring:message code="eurobusiness.actions.cityActions.maxProperty"/>
    </c:otherwise>
</c:choose>