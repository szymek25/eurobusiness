<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<spring:message code="eurobusiness.actions.listoffreecities.list"/>

<c:forEach items="${cities}" var="city" varStatus="status">
    <tr>
        <td><a href="/game-${game.name}/${player.id}/buyCity/${city.id}">${city.name} </a>${city.price}</td>
    </tr>
</c:forEach>