package app.controller;

import app.service.GameApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final GameApiService gameApiService;

    @GetMapping("/home")
    public ModelAndView home(Authentication authentication) {

        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("username", authentication.getName());
        modelAndView.addObject("latestGames", gameApiService.getLatestGames());

        return modelAndView;
    }
}
