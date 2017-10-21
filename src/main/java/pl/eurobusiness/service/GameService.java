package pl.eurobusiness.service;

import pl.eurobusiness.domain.Game;
import pl.eurobusiness.dto.GameDTO;

import java.util.List;

public interface GameService {

    Game createGame(GameDTO gameDTO);

    Game getGameByName(String name);

    List<Game> getAllGames();
}
