package com.example.movie_theater.Controller;

import com.example.movie_theater.Entity.Category;
import com.example.movie_theater.Model.res.CategoryRes;
import com.example.movie_theater.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/category")
@CrossOrigin(origins="http://localhost:3000")

public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity<?> getAll()
    {
        try {
            List<Category> categories = (List<Category>) categoryRepository.findAll();
            List<CategoryRes> categoryRes = categories.stream().map(category -> {
                return CategoryRes.valueOf(category);
            }).collect(Collectors.toList());
            return new ResponseEntity<>(categoryRes, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody Category category){
        Category foundCategory = categoryRepository.findByName(category.getName());
        if(foundCategory!=null) return new ResponseEntity<>("name has been exits", HttpStatus.FORBIDDEN);
        categoryRepository.save(category);
        return new ResponseEntity<>(category,HttpStatus.CREATED);
    }
}
