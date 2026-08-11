package app.controller;

import app.service.GameApiService;
import app.service.UserGameService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.UUID;

@Controller
public class GameController {

    private final GameApiService gameApiService;
    private final UserGameService userGameService;

    public GameController(GameApiService gameApiService, UserGameService userGameService) {
        this.gameApiService = gameApiService;
        this.userGameService = userGameService;
    }

    @GetMapping("/games")
    public ModelAndView getGames() {

        ModelAndView modelAndView = new ModelAndView("games");
        modelAndView.addObject("games", gameApiService.getAllGames());

        return modelAndView;
    }

    @PostMapping("/games/{id}/add")
    @ResponseBody
    public ResponseEntity<String> addGame(@PathVariable UUID id, Principal principal) {

        boolean added = userGameService.addToGameLibrary(principal.getName(), id);

        if (!added) {
            return ResponseEntity.status(409).body("exists");
        }
        return ResponseEntity.ok("added");
    }
}
