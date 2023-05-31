package com.example.movie_theater.Controller;

import com.example.movie_theater.Entity.Category;
import com.example.movie_theater.Entity.Movie;
import com.example.movie_theater.Model.res.MovieRes;
import com.example.movie_theater.Repository.CategoryRepository;
import com.example.movie_theater.Repository.MovieRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Controller
@RequestMapping( "/movie")
@CrossOrigin(origins="http://localhost:3000")
public class MovieController {
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity<?> getAll()
    {
        List<Movie> movies = (List<Movie>) movieRepository.findAll();
        List<MovieRes> movieRes=movies.stream().map(movie -> {return MovieRes.valueOf(movie);}).collect(Collectors.toList());
        return new ResponseEntity<>(movieRes, HttpStatus.OK);
    }
    @RequestMapping(value = "/getDetail/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getDetail(@PathVariable int id)
    {
        Optional<Movie> movie= movieRepository.findById(id);
        if(!movie.isPresent()){
            return new ResponseEntity<>("not found",HttpStatus.NOT_FOUND);
        }
        MovieRes movieRes= MovieRes.valueOf(movie.get());
        return new ResponseEntity<>(movieRes, HttpStatus.OK);
    }
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Movie req )
    {
        Movie movie=null;
        try{

            if(movieRepository.findById(id).isPresent())
            {
                movie=movieRepository.findById(id).get();

                if(req.getName()!=null&&!req.getName().toString().trim().isEmpty()) movie.setName(req.getName());
                if(req.getDescription()!=null&&!req.getDescription().toString().isEmpty()) movie.setDescription(req.getDescription());
                if(req.getDirector()!=null&&!req.getDirector().toString().isEmpty()) movie.setDirector(req.getDirector());
                if(req.getPosterUrl()!=null&&!req.getPosterUrl().toString().isEmpty()) movie.setPosterUrl(req.getPosterUrl());
                if(req.getVote()>0) movie.setVote(movie.getVote()+1);
                movieRepository.save(movie);
            }
            else {
                return new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);
            }
        }
        catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody Movie movie )
    {
        Movie foundMovie= movieRepository.findByName(movie.getName());
        if(foundMovie!=null) return new ResponseEntity<>("name has been exits", HttpStatus.FORBIDDEN);
        movieRepository.save(movie);
        return new ResponseEntity<>(movie, HttpStatus.CREATED);
    }
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable int id )
    {
        movieRepository.deleteById(id);
        return new ResponseEntity<>("delete success", HttpStatus.OK);
    }
    @RequestMapping(value = "/getByCategory/{categoryId}", method = RequestMethod.GET)
    public ResponseEntity<?>getByCategory(@PathVariable int categoryId)
    {
        Optional<Category> category = categoryRepository.findById(categoryId);

        if(category.isPresent())
        {
            List<Movie> movies = movieRepository.findByCategories(category);
            List<MovieRes> movieRes=  movies.stream().map(movie->{return MovieRes.valueOf(movie);}).collect(Collectors.toList());
            System.out.println("movies "+movies);

            return new ResponseEntity<>(movieRes, HttpStatus.OK);
        }
        return new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);
    }
}
