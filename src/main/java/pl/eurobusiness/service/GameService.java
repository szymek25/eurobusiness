package pl.eurobusiness.service;

import pl.eurobusiness.domain.Game;
import pl.eurobusiness.dto.GameDTO;

public interface GameService {

    Game createGame(GameDTO gameDTO);

    Game getGameByName(String name);
}
