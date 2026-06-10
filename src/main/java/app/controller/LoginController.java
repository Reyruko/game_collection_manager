package app.controller;

import app.exception.InvalidUsernameOrPasswordException;
import app.model.dto.user.UserDTO;
import app.model.dto.user.UserLoginRequest;
import app.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public ModelAndView getLoginPage(){
        UserLoginRequest userLoginRequest = UserLoginRequest.builder().build();

        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("userLoginData", userLoginRequest);

        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView login(@Valid @ModelAttribute("userLoginData") UserLoginRequest userLoginRequest,
                              BindingResult bindingResult, HttpSession session) {

        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("login");
            modelAndView.addObject("userLoginData", userLoginRequest);

            return modelAndView;
        }

        try {
            UserDTO user = userService.login(userLoginRequest);

            session.setAttribute("userId", user.getId());
            session.setAttribute("role", user.getRole());

            return new ModelAndView("redirect:/");

        } catch (InvalidUsernameOrPasswordException e) {

            modelAndView.setViewName("login");

            modelAndView.addObject("userLoginData", userLoginRequest);

            modelAndView.addObject("error",
                    "Wrong username or password!");

        }

        return modelAndView;
    }

}
