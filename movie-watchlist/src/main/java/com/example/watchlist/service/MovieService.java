package com.example.watchlist.service;

import com.example.watchlist.dto.MovieRequest;
import com.example.watchlist.dto.MovieResponse;
import com.example.watchlist.exception.MovieNotFoundException;
import com.example.watchlist.model.Movie;
import com.example.watchlist.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

// @RequiredArgsConstructor (Lombok) writes the constructor for the final field,
// and Spring uses it to inject the repository.
@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieResponse add(MovieRequest request) {
        Movie movie = new Movie(request.title(), request.genre(), request.rating());
        return MovieResponse.from(movieRepository.save(movie));
    }

    public MovieResponse get(Long id) {
        return MovieResponse.from(find(id));
    }

    public List<MovieResponse> list(Boolean watched) {
        List<Movie> movies = (watched == null)
                ? movieRepository.findAll()
                : movieRepository.findByWatched(watched);
        return movies.stream().map(MovieResponse::from).toList();
    }

    public MovieResponse update(Long id, MovieRequest request) {
        Movie movie = find(id);
        movie.setTitle(request.title());
        movie.setGenre(request.genre());
        movie.setRating(request.rating());
        return MovieResponse.from(movieRepository.save(movie));
    }

    public MovieResponse markWatched(Long id) {
        Movie movie = find(id);
        movie.setWatched(true);
        return MovieResponse.from(movieRepository.save(movie));
    }

    public void delete(Long id) {
        movieRepository.delete(find(id));
    }

    private Movie find(Long id) {
        return movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException(id));
    }
}
