package app.controller;

import app.service.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
    public ModelAndView getGames() {

        ModelAndView modelAndView = new ModelAndView("games");
        modelAndView.addObject("games", gameService.getAllGames());

        return modelAndView;
    }

    @PostMapping("/games/{id}/add")
    @ResponseBody
    public ResponseEntity<String> addGame(@PathVariable UUID id, Principal principal) {

        boolean added = gameService.addToGameLibrary(principal.getName(), id);

        if (!added) {
            return ResponseEntity.status(409).body("exists");
        }
        return ResponseEntity.ok("added");
    }
}
