package br.rpe.challenger.view.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");


    @ExceptionHandler(CarNotFoundException.class)
    public ResponseEntity handleCarNotFoundException(CarNotFoundException exception){
        Map<String, Object> errors = new HashMap<>();
            errors.put("message", exception.getMessage());

        ExceptionDetails details = new ExceptionDetails(
                LocalDateTime.now().format(formatter),
                HttpStatus.NOT_FOUND.value(),
                exception.getClass().getSimpleName(),
                errors);

            return new ResponseEntity(details, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(CarAlreadyExistsException.class)
    public ResponseEntity handleCarAlreadyExistsException(CarAlreadyExistsException exception){
        Map<String, Object> errors = new HashMap<>();
        errors.put("message", exception.getMessage());

        ExceptionDetails details = new ExceptionDetails(
                LocalDateTime.now().format(formatter),
                HttpStatus.CONFLICT.value(),
                exception.getClass().getSimpleName(),
                errors);

        return new ResponseEntity(details, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Map<String, Object> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().stream().forEach((error) -> {
                    String field = ((FieldError) error).getField();
                    String message = error.getDefaultMessage();
                    errors.put(field, message);
                }
        );

        ExceptionDetails details = new ExceptionDetails(
                LocalDateTime.now().format(formatter),
                HttpStatus.BAD_REQUEST.value(),
                exception.getClass().getSimpleName(),
                errors);

        return new ResponseEntity(details, HttpStatus.BAD_REQUEST);
    }
}
