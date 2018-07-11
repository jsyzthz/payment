package me.jtx.robinia.payment.exception;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import me.jtx.robinia.payment.trade.Result;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final String UNEXPECTED_ERROR = "Exception.unexpected";
    private final MessageSource messageSource;

    @Autowired
    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(PaymentException.class)
    public ResponseEntity<Result> handleIllegalArgument(PaymentException ex, Locale locale) {
        String errorMessage = messageSource.getMessage(ex.getMessage(), ex.getArgs(), locale);
        return new ResponseEntity<>(Result.createByErrorResultMessage(errorMessage), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Result> handleArgumentNotValidException(MethodArgumentNotValidException ex, Locale locale) {
        BindingResult result = ex.getBindingResult();
        List<String> errorMessages = result.getAllErrors().stream()
            .map(objectError -> messageSource.getMessage(objectError, locale)).collect(Collectors.toList());
        return new ResponseEntity<>(Result.createByErrorResultMessage(errorMessages), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Result> handleExceptions(Exception ex, Locale locale) {
        String errorMessage = messageSource.getMessage(UNEXPECTED_ERROR, null, locale);
        ex.printStackTrace();
        return new ResponseEntity<>(Result.createByErrorResultMessage(errorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}