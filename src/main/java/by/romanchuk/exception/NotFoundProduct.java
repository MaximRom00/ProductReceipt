package by.romanchuk.exception;

public class NotFoundProduct extends ProductException{

    public NotFoundProduct() {
        super();
    }

    public NotFoundProduct(String message) {
        super(message);

    }

    public NotFoundProduct(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundProduct(Throwable cause) {
        super(cause);
    }

    protected NotFoundProduct(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
