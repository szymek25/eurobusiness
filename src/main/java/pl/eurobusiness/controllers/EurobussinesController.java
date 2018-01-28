package pl.eurobusiness.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.eurobusiness.EurobusinesConstants;
import pl.eurobusiness.domain.Game;
import pl.eurobusiness.dto.GameDTO;
import pl.eurobusiness.service.GameService;

import javax.servlet.http.HttpServletRequest;


@EnableAutoConfiguration
@Controller
public class EurobussinesController {

    @Autowired
    GameService gameService;

    @RequestMapping("/")
    public String getMenu() {
        return "menu";
    }

    @RequestMapping("newGame")
    public String CreateGame(HttpServletRequest request, @ModelAttribute("playerList") GameDTO gameDTO, BindingResult result) {
        if (request.getMethod().equalsIgnoreCase("post") && !result.hasErrors()) {
            Game game = gameService.createGame(gameDTO);
            request.getSession().setAttribute(EurobusinesConstants.CURRENT_GAME, game);
            return "redirect:/game-" + gameDTO.getName();
        }
        return "createNewGame";
    }

    @RequestMapping("loadGame")
    public String loadGame(Model model) {
        model.addAttribute("games", gameService.getAllGames());
        return "games";
    }

    @RequestMapping("game-{name}")
    public String gameView(HttpServletRequest request, @PathVariable("name") String name, Model model) {
        Game game = gameService.getGameByName(name);
        request.getSession().setAttribute(EurobusinesConstants.CURRENT_GAME, game);
        model.addAttribute("game", game);
        return "game";
    }
}
