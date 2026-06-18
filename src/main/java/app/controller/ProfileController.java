package app.controller;

import app.exception.PasswordMismatchException;
import app.model.dto.user.ChangePasswordRequest;
import app.model.dto.user.UserDTO;
import app.model.dto.user.UserEditProfileRequest;
import app.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class ProfileController {

    private final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public ModelAndView getProfilePage(Authentication authentication) {

        String username = authentication.getName();

        UserDTO userDTO = userService.findByUsername(username);

        ModelAndView modelAndView = new ModelAndView("profile");
        modelAndView.addObject("user", userDTO);

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
    public String changePassword(@ModelAttribute("changePasswordRequest") ChangePasswordRequest dto, Principal principal,
            BindingResult bindingResult,
            Model model) {

        try {
            userService.changePassword(principal.getName(), dto);

            return "redirect:/profile";
        } catch (PasswordMismatchException e) {
            bindingResult.rejectValue("currentPassword", "password.invalid", e.getMessage());
            populateProfileEditModel(model, principal.getName());

            return "profile-edit";
        }
    }

    private void populateProfileEditModel(Model model, String username) {
        UserDTO user = userService.findByUsername(username);

        UserEditProfileRequest dto = new UserEditProfileRequest();
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setBio(user.getBio());

        model.addAttribute("user", dto);
        model.addAttribute("changePasswordRequest", new ChangePasswordRequest());
    }

}
