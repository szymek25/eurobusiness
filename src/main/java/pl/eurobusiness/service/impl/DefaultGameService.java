package pl.eurobusiness.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.eurobusiness.dao.GameDAO;
import pl.eurobusiness.dao.PlayerDAO;
import pl.eurobusiness.domain.Game;
import pl.eurobusiness.domain.Player;
import pl.eurobusiness.dto.GameDTO;
import pl.eurobusiness.service.GameService;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultGameService implements GameService {
    @Autowired
    PlayerDAO playerDAO;

    @Autowired
    GameDAO gameDAO;

    @Override
    public Game createGame(GameDTO gameDTO) {
        Game newGame = new Game();
        List<Player> playerList = new ArrayList<Player>();
        Player player1 = new Player();
        Player player2 = new Player();
        Player player3 = new Player();
        Player player4 = new Player();

        player1.setName(gameDTO.getPlayer1Name());
        player1.setGame(newGame);
        playerList.add(player1);
        player2.setName(gameDTO.getPlayer2Name());
        player2.setGame(newGame);
        playerList.add(player2);
        player3.setName(gameDTO.getPlayer3Name());
        player3.setGame(newGame);
        playerList.add(player3);
        player4.setName(gameDTO.getPlayer4Name());
        player4.setGame(newGame);
        playerList.add(player4);
        newGame.setName(gameDTO.getName());

        gameDAO.save(newGame);
        playerDAO.save(playerList);
        return newGame;
    }

    @Override
    public Game getGameByName(String name) {
        return gameDAO.findByName(name);
    }

}
