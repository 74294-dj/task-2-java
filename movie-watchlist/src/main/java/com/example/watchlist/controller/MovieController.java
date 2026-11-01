package com.example.watchlist.controller;

import com.example.watchlist.dto.MovieRequest;
import com.example.watchlist.dto.MovieResponse;
import com.example.watchlist.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // 201
    @Operation(summary = "Add a movie to the watchlist")
    public MovieResponse add(@Valid @RequestBody MovieRequest request) {
        return movieService.add(request);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get one movie")
    public MovieResponse get(@PathVariable("id") Long id) {
        return movieService.get(id);
    }

    @GetMapping
    @Operation(summary = "List movies, optionally only watched=true or watched=false")
    public List<MovieResponse> list(
            @RequestParam(name = "watched", required = false) Boolean watched) {
        return movieService.list(watched);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Replace title, genre and rating of a movie")
    public MovieResponse update(@PathVariable("id") Long id,
                                @Valid @RequestBody MovieRequest request) {
        return movieService.update(id, request);
    }

    @PatchMapping("/{id}/watched")
    @Operation(summary = "Mark a movie as watched")
    public MovieResponse markWatched(@PathVariable("id") Long id) {
        return movieService.markWatched(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    @Operation(summary = "Remove a movie")
    public void delete(@PathVariable("id") Long id) {
        movieService.delete(id);
    }
}
