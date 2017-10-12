package pl.eurobusiness.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.eurobusiness.dto.PlayersDTO;
import pl.eurobusiness.service.GameService;

import javax.servlet.http.HttpServletRequest;

@EnableAutoConfiguration
@Controller
public class EurobussinesController {

    @Autowired
    GameService gameService;

    @RequestMapping("/")
    public String getMenu(){
        return "menu";
    }

    @RequestMapping("newGame")
    public String CreateGame(HttpServletRequest request, @ModelAttribute("playerList")PlayersDTO playersDTO, BindingResult result){
        if (request.getMethod().equalsIgnoreCase("post") && !result.hasErrors()) {
            gameService.createGame(playersDTO);
            return "redirect:/game";
        }
        return "createNewGame";
    }
}
