package app.controller;

import app.exception.UsernameAlreadyExistsException;
import app.model.dto.user.UserRegisterRequest;
import app.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public ModelAndView getRegisterPage() {

        UserRegisterRequest userRegisterRequest = UserRegisterRequest.builder().build();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register");
        modelAndView.addObject("userRegisterRequest", userRegisterRequest);

        return modelAndView;
    }
    @PostMapping("/register")
    public ModelAndView register(
            @Valid @ModelAttribute("userRegisterRequest") UserRegisterRequest userRegisterRequest,
            BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            ModelAndView mv = new ModelAndView("register");
            mv.addObject("userRegisterRequest", userRegisterRequest);

            return mv;
        }

        try {
            userService.register(userRegisterRequest);

        } catch (UsernameAlreadyExistsException e) {
            ModelAndView modelAndView = new ModelAndView("register");

            modelAndView.addObject("userRegisterRequest");
            modelAndView.addObject("usernameError", e.getMessage());

            return modelAndView;
        }

        return new ModelAndView("redirect:/register/success");
    }

    @GetMapping("/register/success")
    public ModelAndView getRegisterSuccessPage() {
        return new ModelAndView("register-success");
    }
}
