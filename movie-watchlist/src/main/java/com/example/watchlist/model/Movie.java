package com.example.watchlist.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "movies")
@Getter
@Setter
@NoArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    // Stored as text ("SCIFI") in the table, not as a number.
    @Enumerated(EnumType.STRING)
    private Genre genre;

    private Integer rating;   // 1-10, optional
    private boolean watched;  // false until you watch it

    public Movie(String title, Genre genre, Integer rating) {
        this.title = title;
        this.genre = genre;
        this.rating = rating;
        this.watched = false;
    }
}
