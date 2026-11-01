package com.example.watchlist.exception;

import com.example.watchlist.dto.ErrorBody;
import com.example.watchlist.model.Genre;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.List;

// One place that turns exceptions from ANY controller into clean JSON errors.
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MovieNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    public ErrorBody handleNotFound(MovieNotFoundException e) {
        return new ErrorBody(e.getMessage(), List.of());
    }

    // Thrown when @Valid fails (blank title, rating 11, ...)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    public ErrorBody handleValidation(MethodArgumentNotValidException e) {
        List<String> details = e.getBindingResult().getFieldErrors().stream()
                .map(error -> String.format("%s %s", error.getField(), error.getDefaultMessage()))
                .toList();
        return new ErrorBody("Validation failed", details);
    }

    // Thrown when the JSON cannot be read at all (broken JSON, unknown genre, ...)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    public ErrorBody handleUnreadable(HttpMessageNotReadableException e) {
        String message = String.format("Malformed request body. Allowed genres: %s",
                Arrays.toString(Genre.values()));
        return new ErrorBody(message, List.of());
    }
}
