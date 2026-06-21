package app.controller;

import app.service.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.UUID;

@Controller
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/games")
    public ModelAndView getGames(@RequestParam(required = false) String filter,
                                 @RequestParam(required = false) String sort) {

        ModelAndView modelAndView = new ModelAndView("games");

        modelAndView.addObject("games", gameService.getAllGames());


        return modelAndView;
    }

    @PostMapping("/games/{id}/add")
    public String addGame(
            @PathVariable UUID id,
            Principal principal
    ){
        gameService.addToGameLibrary(principal.getName(), id);

        return "redirect:/games";
    }
}
