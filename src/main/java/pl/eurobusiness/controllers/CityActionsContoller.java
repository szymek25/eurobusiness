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
import pl.eurobusiness.service.PlayerService;

@Controller
public class CityActionsContoller {

    private static String ERROR_LOW_MONEY = "error";
    @Autowired
    CityService cityService;
    @Autowired
    PlayerService playerService;

    @RequestMapping("game-{name}/{player}/{cityId}")
    public String showActionsForCity(@PathVariable("name") String gameName, @PathVariable("cityId") Integer cityId, @PathVariable("player") Integer playerId, Model model) {
        addRedirectAttributesToModel(gameName, playerId, cityId, model);
        model.addAttribute("city", cityService.getCityById(cityId));
        return "cityActions";
    }

    @RequestMapping("game-{name}/{player}/{cityId}/build")
    public String buildCity(@PathVariable("cityId") Integer cityId, @PathVariable("player") Integer playerId, Model model, RedirectAttributes redirectAttributes) {
        City city = cityService.getCityById(cityId);
        Player player = playerService.getPlayerById(playerId);
        try {
            cityService.buildProperty(city, player);
        } catch (PayException e) {
            redirectAttributes.addFlashAttribute(ERROR_LOW_MONEY, e.getMessage());
        }
        return "redirect:/game-{name}/{player}/{cityId}";
    }

    private void addRedirectAttributesToModel(String gameName, Integer playerId, Integer cityId, Model model) {
        model.addAttribute("game", gameName);
        model.addAttribute("cityId", cityId);
        model.addAttribute("player", playerId);
    }
}
