package com.example.watchlist.dto;

import java.util.List;

// JSON returned when a request fails: {"message": "...", "details": ["..."]}
public record ErrorBody(String message, List<String> details) {
}
