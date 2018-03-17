<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<c:if test="${not empty succes}">
    <p> ${succes} </p>
</c:if>

<p><a href="/${player}/buyCity"><spring:message code="eurobusiness.actions.buycity"/> </a></p>
<p><a href="/${player}/cities"><spring:message code="eurobusiness.actions.showCities"/> </a></p>
<p><a href="/${player}/pay"><spring:message code="eurobusiness.actions.pay"/> </a></p>
<p><a href="/${player}/selectStopCity"><spring:message code="eurobusiness.actions.stopCity"/> </a></p>

<p><a href="/game-${game.name}"> <spring:message code="eurobusiness.actions.back"/> </a></p>