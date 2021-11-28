package by.romanchuk.exception;

public class FileIllegalStateException extends RuntimeException{
    public FileIllegalStateException() {
    }

    public FileIllegalStateException(String message) {
        super(message);
    }

    public FileIllegalStateException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileIllegalStateException(Throwable cause) {
        super(cause);
    }

    protected FileIllegalStateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
