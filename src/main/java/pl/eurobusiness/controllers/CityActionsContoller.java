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
import pl.eurobusiness.service.MessageService;
import pl.eurobusiness.service.PlayerService;

@Controller
public class CityActionsContoller {

    private static String ERROR_LOW_MONEY = "error";
    private static String SUCCES_MESSAGE = "succes";
    @Autowired
    CityService cityService;
    @Autowired
    PlayerService playerService;
    @Autowired
    GameService gameService;
    @Autowired
    MessageService messageService;

    @RequestMapping("game-{game}/{player}/{cityId}")
    public String showActionsForCity(@PathVariable("game") String gameName, @PathVariable("cityId") Integer cityId, @PathVariable("player") Integer playerId, Model model) {
        model.addAttribute("game", gameService.getGameByName(gameName));
        model.addAttribute("player", playerService.getPlayerById(playerId));
        model.addAttribute("city", cityService.getCityById(cityId));
        return "cityActions";
    }

    @RequestMapping("game-{game}/{player}/{cityId}/build")
    public String buildCity(@PathVariable("cityId") Integer cityId, @PathVariable("player") Integer playerId,@PathVariable("game")String game, Model model, RedirectAttributes redirectAttributes) {
        City city = cityService.getCityById(cityId);
        Player player = playerService.getPlayerById(playerId);
        try {
            cityService.buildProperty(city, player);
            redirectAttributes.addFlashAttribute(SUCCES_MESSAGE, messageService.get("eurobusiness.actions.succes"));
        } catch (PayException e) {
            redirectAttributes.addFlashAttribute(ERROR_LOW_MONEY, e.getMessage());
        }
        return "redirect:/game-{game}/{player}/{cityId}";
    }

}
