package pl.eurobusiness.controllers;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.eurobusiness.EurobusinesConstants;
import pl.eurobusiness.domain.City;
import pl.eurobusiness.domain.Game;
import pl.eurobusiness.domain.Player;
import pl.eurobusiness.dto.PayForm;
import pl.eurobusiness.exceptions.PayException;
import pl.eurobusiness.service.CityService;
import pl.eurobusiness.service.MessageService;
import pl.eurobusiness.service.PayService;
import pl.eurobusiness.service.PlayerService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ActionsController {

    public static final String BANK = "bank";
    @Autowired
    CityService cityService;
    @Autowired
    PlayerService playerService;
    @Autowired
    PayService payService;
    @Autowired
    MessageService messageService;

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

    @RequestMapping("/{player}/pay")
    public String selectPlayerToPay(HttpServletRequest request, RedirectAttributes redirectAttributes, @PathVariable("player") Integer playerId, Model model, @ModelAttribute("payForm") PayForm payForm, BindingResult result) {
        Player player = playerService.getPlayerById(playerId);
        if (request.getMethod().equalsIgnoreCase("post") && !result.hasErrors()) {
            try {
                if (payForm.getDestination().equals(BANK)) {
                    payService.pay(player, payForm.getValue());
                } else {
                    Player destinationPlayer = playerService.getPlayerById(Integer.parseInt(payForm.getDestination()));
                    payService.payToPlayer(player, destinationPlayer, payForm.getValue());
                }
                redirectAttributes.addFlashAttribute(EurobusinesConstants.SUCCES_MESSAGE, messageService.get("eurobusiness.actions.succes"));
            } catch (PayException e) {
                redirectAttributes.addFlashAttribute(EurobusinesConstants.ERROR_LOW_MONEY, e.getMessage());
                return "redirect:/{player}/pay";
            }

            return "redirect:/game/{player}";
        }

        List<Player> players = Lists.newArrayList(playerService.getAllPlayers());
        players = players.stream()
                .filter(player1 -> !player1.equals(player))
                .collect(Collectors.toList());
        model.addAttribute("players", players);
        model.addAttribute("player", player);

        return "selectPayDestination";
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
