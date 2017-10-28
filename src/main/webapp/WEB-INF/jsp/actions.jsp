<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<p><a href="/game-${game.name}/${player}/buyCity"><spring:message code="eurobusiness.actions.buycity"/> </a></p>
<p><a href="/game-${game.name}/${player}/cities"><spring:message code="eurobusiness.actions.showCities"/> </a></p>

<p><a href="/game-${game.name}"> <spring:message code="eurobusiness.actions.back"/> </a></p>