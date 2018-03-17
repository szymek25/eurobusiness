package pl.eurobusiness.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.eurobusiness.EurobusinesConstants;
import pl.eurobusiness.domain.City;
import pl.eurobusiness.domain.Player;
import pl.eurobusiness.exceptions.PayException;
import pl.eurobusiness.service.CityService;
import pl.eurobusiness.service.GameService;
import pl.eurobusiness.service.MessageService;
import pl.eurobusiness.service.PlayerService;

@Controller
public class CityActionsContoller {


    @Autowired
    CityService cityService;
    @Autowired
    PlayerService playerService;
    @Autowired
    GameService gameService;
    @Autowired
    MessageService messageService;

    @RequestMapping("/{player}/{cityId}")
    public String showActionsForCity(@PathVariable("cityId") Integer cityId, @PathVariable("player") Integer playerId, Model model) {
        model.addAttribute("player", playerService.getPlayerById(playerId));
        model.addAttribute("city", cityService.getCityById(cityId));
        return "cityActions";
    }

    @RequestMapping("/{player}/{cityId}/build")
    public String buildCity(@PathVariable("cityId") Integer cityId, @PathVariable("player") Integer playerId, RedirectAttributes redirectAttributes) {
        City city = cityService.getCityById(cityId);
        Player player = playerService.getPlayerById(playerId);
        try {
            cityService.buildProperty(city, player);
            redirectAttributes.addFlashAttribute(EurobusinesConstants.SUCCES_MESSAGE, messageService.get("eurobusiness.actions.succes"));
        } catch (PayException e) {
            redirectAttributes.addFlashAttribute(EurobusinesConstants.ERROR_LOW_MONEY, e.getMessage());
        }
        return "redirect:/{player}/{cityId}";
    }

    @RequestMapping("/{player}/selectStopCity")
    public String selectStopCity(@PathVariable("player") Integer playerId, Model model) {
        model.addAttribute("cities", cityService.getSoldCitiesWithoutCurrentPlayer(playerId));
        model.addAttribute("player", playerService.getPlayerById(playerId));

        return "soldCities";
    }

    @RequestMapping("/{player}/{city}/{owner}")
    public String payForStopInCity(@PathVariable("player") Integer playerId, @PathVariable("city") Integer cityId, @PathVariable("owner") Integer ownerId,RedirectAttributes redirectAttributes) {
        Player player = playerService.getPlayerById(playerId);
        Player owner = playerService.getPlayerById(ownerId);
        City city = cityService.getCityById(cityId);

        try {
            cityService.payForStopInCity(player, city, owner);
            redirectAttributes.addFlashAttribute(EurobusinesConstants.SUCCES_MESSAGE, messageService.get("eurobusiness.actions.succes"));
        } catch (PayException e) {
            redirectAttributes.addFlashAttribute(EurobusinesConstants.ERROR_LOW_MONEY, e.getMessage());
        }

        return "redirect:/{player}/selectStopCity";
    }
}
