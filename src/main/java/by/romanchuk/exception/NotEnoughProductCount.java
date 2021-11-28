package by.romanchuk.exception;

public class NotEnoughProductCount extends ProductException{
    public NotEnoughProductCount() {
        super();
    }

    public NotEnoughProductCount(String message) {
        super(message);
    }

    public NotEnoughProductCount(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughProductCount(Throwable cause) {
        super(cause);
    }

    protected NotEnoughProductCount(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
