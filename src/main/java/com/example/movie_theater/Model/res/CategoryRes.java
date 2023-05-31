package com.example.movie_theater.Model.res;

import com.example.movie_theater.Entity.Category;
import com.example.movie_theater.Entity.Movie;
import lombok.*;

import java.util.Collection;
import java.util.Collections;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryRes {
    private int id;
    private String name;
    private String description;
    private Collection<Movie> movies;
    public static CategoryRes valueOf(Category category)
    {
        return CategoryRes.builder().id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .movies(category.getMovies())
                .build();
    }
}
