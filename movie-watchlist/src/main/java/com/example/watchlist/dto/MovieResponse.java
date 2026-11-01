package com.example.watchlist.dto;

import com.example.watchlist.model.Genre;
import com.example.watchlist.model.Movie;

// The JSON a client receives.
public record MovieResponse(Long id, String title, Genre genre, Integer rating, boolean watched) {

    // Factory method: builds a response straight from an entity (no separate mapper class).
    public static MovieResponse from(Movie movie) {
        return new MovieResponse(movie.getId(), movie.getTitle(), movie.getGenre(),
                movie.getRating(), movie.isWatched());
    }
}
