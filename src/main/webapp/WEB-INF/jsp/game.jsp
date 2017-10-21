<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach items="${game.playerList}" var="player" varStatus="status">
    <tr>
        <td><a href="/game-${game.name}/${player.id}">${player.name}</a></td>
    </tr>
</c:forEach>