package by.epam.voucher.exception;

public class IllegalVoucherException extends Exception {
    public IllegalVoucherException() {
    }

    public IllegalVoucherException(String message) {
        super(message);
    }

    public IllegalVoucherException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalVoucherException(Throwable cause) {
        super(cause);
    }

    public IllegalVoucherException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
