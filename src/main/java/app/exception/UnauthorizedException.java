package app.exception;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException() {
        super("Authorised user expected.");
    }
}
