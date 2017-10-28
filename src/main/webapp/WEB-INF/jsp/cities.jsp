<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<c:forEach items="${cities}" var="city">
    <a href="/game-${game.name}/${player.id}/${city.id}">${city.name}</a>
    <c:choose>
        <c:when test="${city.quantityOfProperty <= 3}">
            <spring:message code="eurobusiness.actions.showCities.houses"/>: ${city.quantityOfProperty}
        </c:when>
        <c:when test="${city.quantityOfProperty == 4}">
            <spring:message code="eurobusiness.actions.showCities.hotel"/>
        </c:when>
    </c:choose>
</c:forEach>

<p><a href="/game-${game.name}/${player.id}"> <spring:message code="eurobusiness.actions.back"/> </a></p>