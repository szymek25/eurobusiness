package pl.eurobusiness.service;

import pl.eurobusiness.domain.Player;

public interface PlayerService {

    Player getPlayerById(Integer playerId);

    Iterable<Player> getAllPlayers();
}
