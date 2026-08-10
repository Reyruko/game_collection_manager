package app.controller;

import app.exception.GameApiException;
import app.model.dto.game.*;
import app.service.GameApiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        addGameFormData(model);

        return "manage-games";
    }

    @GetMapping("/add")
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    public String showAddGameForm(Model model) {

        model.addAttribute("game", new GameCreateRequest());
        model.addAttribute("genre", new GenreCreateRequest());
        model.addAttribute("platform", new PlatformCreateRequest());
        addGameFormData(model);

        return "add-game";
    }

    @PutMapping("/edit/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    @ResponseBody
    public GameDTO updateGame(
            @PathVariable UUID id,
            @Valid @RequestBody GameUpdateRequest request) {

        return gameApiService.updateGame(id, request);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    @PostMapping("/add")
    public String addGame(@Valid @ModelAttribute("game") GameCreateRequest game,
                          BindingResult bindingResult,
                          Model model) {

        if (bindingResult.hasErrors()) {

            addGameFormData(model);
            model.addAttribute("genre", new GenreCreateRequest());
            model.addAttribute("platform", new PlatformCreateRequest());

            return "add-game";
        }

        try {
            gameApiService.createGame(game);
        } catch (GameApiException ex) {

            model.addAttribute("gameError", "Game already exists!");

            addGameFormData(model);
            model.addAttribute("genre", new GenreCreateRequest());
            model.addAttribute("platform", new PlatformCreateRequest());

            return "add-game";
        }

        model.addAttribute("success", true);
        model.addAttribute("game", new GameCreateRequest());
        model.addAttribute("genre", new GenreCreateRequest());
        model.addAttribute("platform", new PlatformCreateRequest());
        addGameFormData(model);

        return "add-game";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/delete/{id}")
    public String deleteGame(@PathVariable UUID id) {

        gameApiService.deleteGame(id);

        return "redirect:/manage/games";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    @PostMapping("/genres/add")
    public String addGenre(
            @Valid @ModelAttribute("genre") GenreCreateRequest genre,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            addGameFormData(model);
            return "add-game";
        }

        gameApiService.createGenre(genre);

        redirectAttributes.addFlashAttribute(
                "genreSuccess",
                "Genre added successfully!"
        );

        return "redirect:/manage/games/add";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    @PostMapping("/platforms/add")
    public String addPlatform(
            @Valid @ModelAttribute("platform") PlatformCreateRequest platform,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            addGameFormData(model);
            return "add-game";
        }

        redirectAttributes.addFlashAttribute(
                "platformSuccess",
                "Platform added successfully!"
        );

        gameApiService.createPlatform(platform);

        return "redirect:/manage/games/add?platformSuccess";
    }

    private void addGameFormData(Model model) {

        model.addAttribute("genres", gameApiService.getAllGenres());
        model.addAttribute("platforms", gameApiService.getAllPlatforms());
    }
}
