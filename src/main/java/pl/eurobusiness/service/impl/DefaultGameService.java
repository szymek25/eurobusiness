package pl.eurobusiness.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.eurobusiness.dao.GameDAO;
import pl.eurobusiness.dao.PlayerDAO;
import pl.eurobusiness.domain.Game;
import pl.eurobusiness.domain.Player;
import pl.eurobusiness.dto.PlayersDTO;
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
    public void createGame(PlayersDTO playersDTO) {
        Game newGame = new Game();
        List<Player> playerList = new ArrayList<Player>();
        Player player1 = new Player();
        Player player2 = new Player();
        Player player3 = new Player();
        Player player4 = new Player();

        player1.setName(playersDTO.getPlayer1Name());
        player1.setGame(newGame);
        playerList.add(player1);
        player2.setName(playersDTO.getPlayer2Name());
        player2.setGame(newGame);
        playerList.add(player2);
        player3.setName(playersDTO.getPlayer3Name());
        player3.setGame(newGame);
        playerList.add(player3);
        player4.setName(playersDTO.getPlayer4Name());
        player4.setGame(newGame);
        playerList.add(player4);

        gameDAO.save(newGame);
        playerDAO.save(playerList);

    }
}
