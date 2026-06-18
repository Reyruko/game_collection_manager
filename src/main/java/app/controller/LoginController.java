package app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @GetMapping("/login")
    public ModelAndView getLoginPage(@RequestParam(required = false) String error){

        ModelAndView modelAndView = new ModelAndView("login");

        if (error != null) {
            modelAndView.addObject("loginError", "Invalid username or password");
        }

        return modelAndView;
    }

}
