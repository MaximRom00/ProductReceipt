package by.romanchuk.exception;

public class DeleteProduct extends ProductException{
    public DeleteProduct() {
        super();
    }

    public DeleteProduct(String message, int id) {
        super(message + id);

    }

    public DeleteProduct(String message, Throwable cause) {
        super(message, cause);
    }

    public DeleteProduct(Throwable cause) {
        super(cause);
    }

    protected DeleteProduct(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
