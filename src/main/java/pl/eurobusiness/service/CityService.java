package pl.eurobusiness.service;

import pl.eurobusiness.domain.City;
import pl.eurobusiness.domain.Player;

import java.util.List;

public interface CityService {
    List<City> getFreeCities();

    boolean buyCity(Player player, City city);

    City getCityById(Integer cityId);
}
