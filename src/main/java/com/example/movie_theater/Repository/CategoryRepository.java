package com.example.movie_theater.Repository;

import com.example.movie_theater.Entity.Category;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {
    Category findByName(@NotNull String name);
}
