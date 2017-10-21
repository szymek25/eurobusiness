package pl.eurobusiness.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.eurobusiness.dao.CityDAO;
import pl.eurobusiness.dao.PlayerDAO;
import pl.eurobusiness.domain.City;
import pl.eurobusiness.domain.Player;
import pl.eurobusiness.service.CityService;

import java.util.List;

@Service
public class DefaultCityService implements CityService {

    @Autowired
    CityDAO cityDAO;

    @Autowired
    PlayerDAO playerDAO;

    @Override
    public List<City> getFreeCities() {
        return cityDAO.findByOwnerIsNull();
    }

    @Override
    public boolean buyCity(Player player, City city) {
        if(player != null && city !=null){
            int playerAmount = player.getAccountAmount();
            if(playerAmount < city.getPrice()){
                return false;
            }
            playerAmount = playerAmount - city.getPrice();
            player.setAccountAmount(playerAmount);
            player.getCityList().add(city);
            city.setOwner(player);
            playerDAO.save(player);
            cityDAO.save(city);
            return true;
        }

        return false;
    }

    @Override
    public City getCityById(Integer cityId) {
        return cityDAO.findOne(cityId);
    }
}
