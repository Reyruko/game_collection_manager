package app.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(GameNotFoundException.class)
    public String handleGameNotFound(
            GameNotFoundException ex,
            Model model) {

        logger.warn("Game not found: {}", ex.getMessage());

        model.addAttribute("error", ex.getMessage());
        model.addAttribute("status", HttpStatus.NOT_FOUND.value());

        return "error";
    }

    @ExceptionHandler(UserNotFoundException.class)
    public String handleUserNotFound(
            UserNotFoundException ex,
            Model model) {

        logger.warn("User not found: {}", ex.getMessage());

        model.addAttribute("error", ex.getMessage());
        model.addAttribute("status", HttpStatus.NOT_FOUND.value());

        return "error";
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public String handleUsernameAlreadyExists(
            UsernameAlreadyExistsException ex,
            Model model) {

        model.addAttribute("error", ex.getMessage());
        model.addAttribute("status", HttpStatus.CONFLICT.value());

        return "error";
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public String handleEmailAlreadyExists(
            EmailAlreadyExistsException ex,
            Model model) {

        model.addAttribute("error", ex.getMessage());
        model.addAttribute("status", HttpStatus.CONFLICT.value());

        return "error";
    }

    @ExceptionHandler(PasswordMismatchException.class)
    public String handlePasswordMismatch(
            PasswordMismatchException ex,
            Model model) {

        model.addAttribute("error", ex.getMessage());
        model.addAttribute("status", HttpStatus.BAD_REQUEST.value());

        return "error";
    }

    @ExceptionHandler(UnauthorizedException.class)
    public String handleUnauthorized(
            UnauthorizedException ex,
            Model model) {

        logger.warn("Unauthorized error: {}", ex.getMessage());

        model.addAttribute("error", ex.getMessage());
        model.addAttribute("status", HttpStatus.UNAUTHORIZED.value());

        return "error";
    }

    @ExceptionHandler(GameApiException.class)
    public String handleGameApiException(
            GameApiException ex,
            Model model) {

        logger.warn("GameApi error: {}", ex.getMessage());

        model.addAttribute("error", ex.getMessage());
        model.addAttribute("status", ex.getStatus());

        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String handleUnknownException(
            Exception ex,
            Model model) {

        logger.error("Unexpected application error", ex);

        model.addAttribute(
                "error",
                "Something went wrong."
        );

        model.addAttribute(
                "status",
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );

        return "error";
    }


}
