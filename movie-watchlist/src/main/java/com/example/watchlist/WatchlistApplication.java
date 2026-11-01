package com.example.watchlist;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
        title = "Movie Watchlist API",
        version = "1.0",
        description = "Keep track of movies you want to watch"))
public class WatchlistApplication {

    public static void main(String[] args) {
        SpringApplication.run(WatchlistApplication.class, args);
    }
}
