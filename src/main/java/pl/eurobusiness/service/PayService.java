package pl.eurobusiness.service;


import pl.eurobusiness.domain.Player;
import pl.eurobusiness.exceptions.PayException;

public interface PayService {
    Player pay(Player player, Integer value)throws PayException;
}
