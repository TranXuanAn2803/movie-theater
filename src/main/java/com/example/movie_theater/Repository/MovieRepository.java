package com.example.movie_theater.Repository;

import com.example.movie_theater.Entity.Category;
import com.example.movie_theater.Entity.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface MovieRepository extends CrudRepository<Movie, Integer> {
    Movie findByName(String name);

    List<Movie> findByCategories(Optional<Category> category);
}
