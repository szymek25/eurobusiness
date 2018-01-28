package pl.eurobusiness.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.eurobusiness.EurobusinesConstants;
import pl.eurobusiness.domain.City;
import pl.eurobusiness.domain.Game;
import pl.eurobusiness.domain.Player;
import pl.eurobusiness.exceptions.PayException;
import pl.eurobusiness.service.CityService;
import pl.eurobusiness.service.PlayerService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ActionsController {

    @Autowired
    CityService cityService;
    @Autowired
    PlayerService playerService;

    @RequestMapping("game/{player}")
    public String getActionViewForPlayer(HttpServletRequest request, @PathVariable("player") Integer playerId, Model model) {
        if (loadGameAndAddToModel(request, model)) {
            return "redirect:/";
        }
        return "actions";
    }


    @RequestMapping("/{player}/buyCity")
    public String getFreeCityList(HttpServletRequest request, @PathVariable("player") Integer playerId, Model model) {
        model.addAttribute("player", playerService.getPlayerById(playerId));
        model.addAttribute("cities", cityService.getFreeCities());

        return "listFreeCities";
    }

    @RequestMapping("/{player}/buyCity/{cityId}")
    public String buyCity(RedirectAttributes redirectAttributes, @PathVariable("cityId") Integer cityId, @PathVariable("player") Integer playerId) {
        Player player = playerService.getPlayerById(playerId);
        City city = cityService.getCityById(cityId);
        try {
            cityService.buyCity(player, city);
        } catch (PayException e) {
            redirectAttributes.addFlashAttribute(EurobusinesConstants.ERROR_LOW_MONEY, e.getMessage());
        }
        return "redirect:../buyCity";
    }

    @RequestMapping("/{player}/cities")
    public String showOwnedCities(@PathVariable("player") Integer playerId, Model model) {
        Player player = playerService.getPlayerById(playerId);
        List<City> cities = cityService.getCityByPlayer(player);
        model.addAttribute("cities", cities);
        model.addAttribute("player", player);
        return "cities";
    }

    private boolean loadGameAndAddToModel(HttpServletRequest request, Model model) {
        Game game = (Game) request.getSession().getAttribute(EurobusinesConstants.CURRENT_GAME);
        if (game == null) {
            return true;
        }
        model.addAttribute("game", game);
        return false;
    }
}
