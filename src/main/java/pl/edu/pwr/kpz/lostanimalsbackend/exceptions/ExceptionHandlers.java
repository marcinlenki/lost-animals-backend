package pl.edu.pwr.kpz.lostanimalsbackend.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlers {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlers.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public Map<String, String> handleRuntimeExceptions(RuntimeException e) {
        logger.error(e.getMessage(), e);
        Map<String, String> errors = new HashMap<>();
        errors.put("cause", e.getMessage());
        return errors;
    }
}
