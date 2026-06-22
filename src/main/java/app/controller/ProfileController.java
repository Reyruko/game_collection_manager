package app.controller;

import app.exception.PasswordMismatchException;
import app.model.dto.user.ChangePasswordRequest;
import app.model.dto.user.UserDTO;
import app.model.dto.user.UserEditProfileRequest;
import app.model.dto.usergame.EditGameLibraryRequest;
import app.model.entity.UserGame;
import app.service.GameService;
import app.service.UserGameService;
import app.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Controller
public class ProfileController {

    private final UserService userService;
    private final UserGameService userGameService;
    private final GameService gameService;

    public ProfileController(UserService userService, UserGameService userGameService, GameService gameService) {
        this.userService = userService;
        this.userGameService = userGameService;
        this.gameService = gameService;
    }

    @GetMapping("/profile")
    public ModelAndView getProfilePage(Authentication authentication) {

        String username = authentication.getName();

        UserDTO userDTO = userService.findByUsername(username);

        List<UserGame> gameLibrary = userGameService.getUserGames(username);

        ModelAndView modelAndView = new ModelAndView("profile");
        modelAndView.addObject("user", userDTO);
        modelAndView.addObject("collection", gameLibrary);

        return modelAndView;
    }

    @GetMapping("/profile/edit")
    public String editProfilePage(Model model, Principal principal) {

        populateProfileEditModel(model, principal.getName());

        return "profile-edit";
    }

    @PostMapping("/profile/edit")
    public String updateProfile(@ModelAttribute("user") UserEditProfileRequest dto, Principal principal,
            BindingResult bindingResult,
            Model model) {

        try {
            userService.updateProfile(principal.getName(), dto);

            return "redirect:/profile";

        } catch (PasswordMismatchException e) {
            bindingResult.rejectValue("currentPassword", "password.invalid", e.getMessage());
            populateProfileEditModel(model, principal.getName());

            return "profile-edit";
        }
    }

    @PostMapping("/profile/password")
    public String changePassword(@Valid @ModelAttribute("changePasswordRequest") ChangePasswordRequest dto,
                                 BindingResult bindingResult,
                                 Principal principal,
                                 Model model) {

        if (bindingResult.hasErrors()) {
            populateProfileEditModel(model, principal.getName());
            return "profile-edit";
        }

        try {
            userService.changePassword(principal.getName(), dto);

            return "redirect:/profile";
        } catch (PasswordMismatchException e) {
            bindingResult.rejectValue("currentPassword", "password.invalid", e.getMessage());
            populateProfileEditModel(model, principal.getName());

            return "profile-edit";
        }
    }

    @PostMapping("gameLibrary/edit/{id}")
    public String editGameStatus(@PathVariable UUID id,
                                 EditGameLibraryRequest editGameLibraryRequest,
                                 Principal principal) {

        gameService.editGameLibrary(principal.getName(), id, editGameLibraryRequest);

        return "redirect:/profile";
    }

    private void populateProfileEditModel(Model model, String username) {
        UserDTO user = userService.findByUsername(username);

        if (!model.containsAttribute("user")) {
            UserEditProfileRequest dto = new UserEditProfileRequest();

            dto.setUsername(user.getUsername());
            dto.setEmail(user.getEmail());
            dto.setBio(user.getBio());

            model.addAttribute("user", dto);
        }

        if(!model.containsAttribute("changePasswordRequest")) {
            model.addAttribute("changePasswordRequest", new ChangePasswordRequest());
        }
    }

}
