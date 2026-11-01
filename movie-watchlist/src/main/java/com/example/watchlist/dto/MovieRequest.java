package com.example.watchlist.dto;

import com.example.watchlist.model.Genre;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// The JSON a client sends. The annotations are the validation rules.
public record MovieRequest(
        @NotBlank(message = "must not be blank") String title,
        @NotNull(message = "is required") Genre genre,
        @Min(value = 1, message = "must be at least 1")
        @Max(value = 10, message = "must be at most 10") Integer rating) {
}
