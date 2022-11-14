package tr.com.obss.jifinalproject.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<?> handleArithmeticException(HttpServletRequest request, Throwable t){
        LOGGER.error(t.getMessage(),t);
        var map = new HashMap<>();
        map.put("error","Arithmetic error occurred!");
        return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleRuntimeException(HttpServletRequest request, Throwable t){
        LOGGER.error(t.getMessage(),t);
        var map = new HashMap<>();
        map.put("error","Unknown error occurred!");
        return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(HttpServletRequest request, MethodArgumentNotValidException t){
        LOGGER.error(t.getMessage(),t);
        var map = new HashMap<>();
        map.put("error",t.getMessage());
        return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}