package com.algaworks.algamoneyapi.exceptions;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {
    private MessageSource messageSource;

    public ApplicationExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String message = messageSource.getMessage("invalid.message", null, LocaleContextHolder.getLocale());
        List<StandardErrorDto> errors = Arrays.asList(new StandardErrorDto(message, ex.getCause().toString()));
        return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<StandardErrorDto> errors = createErrorsList(ex.getBindingResult());
        return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ ResourceNotFoundException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleResourceNotFoundException() { }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
        String message = messageSource.getMessage("invalid.operation", null, LocaleContextHolder.getLocale());
        String developerMessage = ExceptionUtils.getRootCauseMessage(ex);
        List<StandardErrorDto> errors = Arrays.asList(new StandardErrorDto(message, developerMessage));
        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({InactivePersonException.class})
    public ResponseEntity<Object> handleInactivePersonException(InactivePersonException ex, WebRequest request) {
        String message = messageSource.getMessage("inactive.person", null, LocaleContextHolder.getLocale());
        String developerMessage = ex.toString();
        List<StandardErrorDto> errors = Arrays.asList(new StandardErrorDto(message, developerMessage));
        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    private List<StandardErrorDto> createErrorsList(BindingResult bindingResult) {
        List<StandardErrorDto> errors = new ArrayList<>();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            String userMessage = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            String developerMessage = fieldError.toString();
            errors.add(new StandardErrorDto(userMessage, developerMessage));
        }

        return errors;
    }
}
