package nl.svb.dms.assessment.customer.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 
 * Custom Exception voor PGB API
 * 
 */
@Getter
public class CustomerException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final HttpStatus httpStatus;

    public CustomerException(final String message) {
        super(message);
        httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public CustomerException(final String message, final HttpStatus returnStatus) {
        super(message);
        httpStatus = returnStatus;
    }

    public CustomerException(final String message, final Throwable throwable) {
        super(message, throwable);
        httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public CustomerException(final String message, final Throwable throwable, final HttpStatus returnStatus) {
        super(message, throwable);
        httpStatus = returnStatus;
    }
}
