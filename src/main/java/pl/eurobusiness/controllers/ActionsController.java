package pl.eurobusiness.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.eurobusiness.domain.City;
import pl.eurobusiness.domain.Player;
import pl.eurobusiness.service.CityService;
import pl.eurobusiness.service.GameService;
import pl.eurobusiness.service.PlayerService;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ActionsController {

    @Autowired
    CityService cityService;

    @Autowired
    PlayerService playerService;

    @Autowired
    GameService gameService;

    @RequestMapping("game-{name}/{player}")
    public String getActionViewForPlayer(HttpServletRequest request, Model model, @PathVariable("player") Integer playerId) {

        return "actions";
    }

    @RequestMapping("/game-{game}/{player}/buyCity")
    public String getFreeCityList(HttpServletRequest request, @PathVariable("game") String game, @PathVariable("player") Integer playerId, Model model) {

        model.addAttribute("player", playerService.getPlayerById(playerId));
        model.addAttribute("game", gameService.getGameByName(game));
        model.addAttribute("cities", cityService.getFreeCities());
        return "listFreeCities";
    }

    @RequestMapping("/game-{game}/{player}/buyCity/{cityId}")
    public String buyCity(@PathVariable("cityId")Integer cityId, @PathVariable("game") String game, @PathVariable("player") Integer playerId){
        Player player = playerService.getPlayerById(playerId);
        City city = cityService.getCityById(cityId);
        cityService.buyCity(player,city);
        return "redirect:/game-{game}/{player}/buyCity";
    }

}
