package pl.eurobusiness.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.eurobusiness.dao.PlayerDAO;
import pl.eurobusiness.domain.Player;
import pl.eurobusiness.exceptions.PayException;
import pl.eurobusiness.service.MessageService;
import pl.eurobusiness.service.PayService;

@Service
public class DefaultPayService implements PayService {
    @Autowired
    MessageService messageService;

    @Autowired
    PlayerDAO playerDAO;

    @Override
    public Player pay(Player player, Integer value) throws PayException {
        int playerAmount = player.getAccountAmount();
        if (playerAmount < value) {
            throw new PayException(messageService.get("eurbusiness.errors.tolowmoney"));
        }
        playerAmount = playerAmount - value;
        player.setAccountAmount(playerAmount);
        playerDAO.save(player);

        return player;
    }
}
