package app.controller;

import app.model.dto.game.GameCreateRequest;
import app.service.GameApiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@Controller
@RequestMapping("/manage/games")
public class GameManagementController {

    private final GameApiService gameApiService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    public String manageGames(Model model) {

        model.addAttribute("games", gameApiService.getAllGames());

        return "manage-games";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    @GetMapping("/add")
    public String showAddGameForm(Model model) {

        model.addAttribute("game", new GameCreateRequest());
        model.addAttribute("genres", gameApiService.getAllGenres());
        model.addAttribute("platforms", gameApiService.getAllPlatforms());

        return "add-game";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    @PostMapping("/add")
    public String addGame(@Valid @ModelAttribute("game") GameCreateRequest game,
                          BindingResult bindingResult,
                          Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("genres", gameApiService.getAllGenres());
            model.addAttribute("platforms", gameApiService.getAllPlatforms());

            return "add-game";
        }

        gameApiService.createGame(game);

        return "redirect:/manage/games";
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/delete/{id}")
    public String deleteGame(@PathVariable UUID id) {

        gameApiService.deleteGame(id);

        return "redirect:/manage/games";
    }

}
