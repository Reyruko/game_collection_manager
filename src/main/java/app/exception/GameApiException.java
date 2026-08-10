package app.exception;

import lombok.Getter;

@Getter
public class GameApiException extends RuntimeException {

    private final int status;

    public GameApiException(int status, String message) {
        super(message);
        this.status = status;
    }

}
