package pl.eurobusiness.service;

import pl.eurobusiness.domain.City;
import pl.eurobusiness.domain.Player;
import pl.eurobusiness.exceptions.PayException;

import java.util.List;

public interface CityService {
    List<City> getFreeCities();

    boolean buyCity(Player player, City city) throws PayException;

    City getCityById(Integer cityId);

    List<City> getCityByPlayer(Player player);

    City buildProperty(City city, Player player) throws PayException;
}
