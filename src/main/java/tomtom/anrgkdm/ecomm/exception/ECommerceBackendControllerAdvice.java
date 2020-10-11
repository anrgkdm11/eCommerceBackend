package tomtom.anrgkdm.ecomm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ECommerceBackendControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ECommerceException.class)
    public ResponseEntity<ExceptionResponse> HandleECommerceException(ECommerceException e){
        return new ResponseEntity<>(ExceptionResponse.builder().message(e.getMessage()).timeStamp(LocalDateTime.now()).build(), e.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> HandleAllException(Exception e){
        return new ResponseEntity<>(ExceptionResponse.builder().message(e.getMessage()).timeStamp(LocalDateTime.now()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
