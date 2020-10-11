package tomtom.anrgkdm.ecomm.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;


@Data
public class ECommerceException extends RuntimeException {

    private String message;
    private HttpStatus httpStatus;


    public ECommerceException(String message, Throwable cause, HttpStatus httpStatus) {
        super(cause);
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public ECommerceException(String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
