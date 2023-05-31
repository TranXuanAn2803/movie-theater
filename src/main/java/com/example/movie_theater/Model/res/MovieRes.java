package com.example.movie_theater.Model.res;

import com.example.movie_theater.Entity.Category;
import com.example.movie_theater.Entity.Movie;
import lombok.*;

import java.util.Collection;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieRes {
    private int id;
    private String name;
    private String description;
    private String director;
    private String posterUrl;
    private int vote;
    private Collection<Category> categories;
    public static MovieRes valueOf(Movie movie)
    {
        return MovieRes.builder().id(movie.getId())
                .name(movie.getName())
                .description(movie.getDescription())
                .director(movie.getDirector())
                .posterUrl(movie.getPosterUrl())
                .categories(movie.getCategories())
                .vote(movie.getVote()).build();
    }
}
