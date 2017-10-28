package pl.eurobusiness.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.eurobusiness.domain.City;
import pl.eurobusiness.domain.Player;
import pl.eurobusiness.exceptions.PayException;
import pl.eurobusiness.service.CityService;
import pl.eurobusiness.service.GameService;
import pl.eurobusiness.service.PlayerService;

import java.util.List;

@Controller
public class ActionsController {

    private static String ERROR_LOW_MONEY = "error";
    @Autowired
    CityService cityService;
    @Autowired
    PlayerService playerService;
    @Autowired
    GameService gameService;

    @RequestMapping("game-{game}/{player}")
    public String getActionViewForPlayer(@PathVariable("player") Integer playerId,@PathVariable("game") String name, Model model) {
        model.addAttribute("game", gameService.getGameByName(name));
        return "actions";
    }

    @RequestMapping("/game-{game}/{player}/buyCity")
    public String getFreeCityList(@PathVariable("game") String game, @PathVariable("player") Integer playerId, Model model) {
        model.addAttribute("player", playerService.getPlayerById(playerId));
        model.addAttribute("game", gameService.getGameByName(game));
        model.addAttribute("cities", cityService.getFreeCities());
        return "listFreeCities";
    }

    @RequestMapping("/game-{game}/{player}/buyCity/{cityId}")
    public String buyCity(RedirectAttributes redirectAttributes, @PathVariable("cityId") Integer cityId, @PathVariable("game") String game, @PathVariable("player") Integer playerId) {
        Player player = playerService.getPlayerById(playerId);
        City city = cityService.getCityById(cityId);
        try {
            cityService.buyCity(player, city);
        } catch (PayException e) {
            redirectAttributes.addFlashAttribute(ERROR_LOW_MONEY, e.getMessage());
        }
        return "redirect:/game-{game}/{player}/buyCity";
    }

    @RequestMapping("game-{game}/{player}/cities")
    public String showOwnedCities(@PathVariable("player") Integer playerId,@PathVariable("game")String game, Model model) {
        Player player = playerService.getPlayerById(playerId);
        List<City> cities = cityService.getCityByPlayer(player);
        model.addAttribute("cities", cities);
        model.addAttribute("player", player);
        model.addAttribute("game" , gameService.getGameByName(game));
        return "cities";
    }

}
