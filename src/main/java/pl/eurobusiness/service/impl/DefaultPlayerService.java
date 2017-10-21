package pl.eurobusiness.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.eurobusiness.dao.PlayerDAO;
import pl.eurobusiness.domain.Player;
import pl.eurobusiness.service.PlayerService;

@Service
public class DefaultPlayerService implements PlayerService {
    @Autowired
    PlayerDAO playerDAO;

    @Override
    public Player getPlayerById(Integer playerId) {
        return playerDAO.findOne(playerId);
    }
}
