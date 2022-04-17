package com.getground.guestlist.adapter.controller;

import com.getground.guestlist.adapter.controller.model.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collection;
import java.util.Objects;

import static java.util.stream.Collectors.toUnmodifiableList;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice("com.getground.guestlist.adapter.controller")
class GlobalControllerAdvice {
    private final static Logger LOGGER = LoggerFactory.getLogger(GlobalControllerAdvice.class);

    @NonNull
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    ErrorResponse handle(@NonNull final RuntimeException exception) {
        LOGGER.error(exception.getMessage(), exception);
        return new ErrorResponse(exception.getMessage());
    }

    @NonNull
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    ErrorResponse handle(@NonNull final IllegalArgumentException exception) {
        LOGGER.error(exception.getMessage(), exception);
        return new ErrorResponse(exception.getMessage());
    }

    @NonNull
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    Collection<ErrorResponse> handle(@NonNull final MethodArgumentNotValidException exception) {
        LOGGER.error(exception.getMessage(), exception);
        return exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .filter(Objects::nonNull)
                .map(ErrorResponse::new)
                .collect(toUnmodifiableList());
    }
}
