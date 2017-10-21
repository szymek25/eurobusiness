<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<p><spring:message code="eurobusiness.games.loadgame"/></p>

<c:forEach items="${games}" var="game">
    <tr>
            <td><a href="/game-${game.name}">${game.name}</a></td>
    </tr>
</c:forEach>