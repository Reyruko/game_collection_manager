package app.exception;

public class UserInactiveException extends RuntimeException {
    public UserInactiveException() {
        super("User is inactive!");
    }
}
