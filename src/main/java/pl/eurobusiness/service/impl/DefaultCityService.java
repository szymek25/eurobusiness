package pl.eurobusiness.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.eurobusiness.dao.CityDAO;
import pl.eurobusiness.dao.PlayerDAO;
import pl.eurobusiness.domain.City;
import pl.eurobusiness.domain.Player;
import pl.eurobusiness.exceptions.PayException;
import pl.eurobusiness.service.CityService;
import pl.eurobusiness.service.MessageService;
import pl.eurobusiness.service.PayService;

import java.util.List;

@Service
public class DefaultCityService implements CityService {

    @Autowired
    CityDAO cityDAO;

    @Autowired
    PlayerDAO playerDAO;

    @Autowired
    MessageService messageService;

    @Autowired
    PayService payService;

    @Override
    public List<City> getFreeCities() {
        return cityDAO.findByOwnerIsNull();
    }

    @Override
    public boolean buyCity(Player player, City city) throws PayException {
        if (player != null && city != null) {
            try {
                payService.pay(player, city.getPrice());
                player.getCityList().add(city);
                city.setOwner(player);
                cityDAO.save(city);
            } catch (PayException e) {
                throw e;
            }
            return true;
        }

        return false;
    }

    @Override
    public City getCityById(Integer cityId) {
        return cityDAO.findOne(cityId);
    }

    @Override
    public List<City> getCityByPlayer(Player player) {
        return cityDAO.findByOwner(player);
    }

    @Override
    public City buildProperty(City city, Player player) throws PayException {
        int quantityOfProperty = city.getQuantityOfProperty();
        int price;
        if (quantityOfProperty < 3) {
            price = city.getPropertyPrice();
        } else if (quantityOfProperty == 3) {
            price = city.getHotelPrice();
        } else {
            return city;
        }

        try {
            payService.pay(player, price);
        } catch (PayException e) {
            throw e;
        }

        quantityOfProperty++;
        city.setQuantityOfProperty(quantityOfProperty);
        cityDAO.save(city);

        return city;
    }

    @Override
    public List<City> getSoldCitiesWithoutCurrentPlayer(int playerId) {
        return cityDAO.findWithoutCurrentPlayer(playerId);
    }

    @Override
    public boolean payForStopInCity(Player player, City city, Player owner) throws PayException {
        int price;
        price = getPriceForStop(city);

        try {
            payService.payToPlayer(player, owner, price);
        } catch (PayException e) {
            throw e;
        }

        return false;
    }

    private int getPriceForStop(City city) {
        int value = 0;
        if (city.getQuantityOfProperty() == 0) {
            value = city.getValueWithoutProperty();
        }

        if (city.getQuantityOfProperty() == 1) {
            value = city.getValueForOneProperty();
        }

        if (city.getQuantityOfProperty() == 2) {
            value = city.getValueForTwoProperty();
        }

        if (city.getQuantityOfProperty() == 3) {
            value = city.getValueForThreeProperty();
        }

        if (city.getQuantityOfProperty() == 4) {
            value = city.getValueForHotel();
        }
        return value;
    }
}
