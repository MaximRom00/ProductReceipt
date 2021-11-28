package by.romanchuk.exception;

public class ArgsException extends RuntimeException{
    public ArgsException(String message) {
        super(message);
    }

    public ArgsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ArgsException(Throwable cause) {
        super(cause);
    }

    protected ArgsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
