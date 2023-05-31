package com.example.movie_theater.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name="movie")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Movie implements Serializable {
    private static final long serialVersionUID = 3056572664881306837L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    @NotNull
    private String name;
    @Column(name="description")
    private String description;
    @Column(name = "director")
    @NotNull
    private String director;
    @Column(name = "poster_url")
    private String posterUrl;
    @Column(name = "vote")
    private int vote;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinTable(name="movie_tag", joinColumns = @JoinColumn(name = "movie_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    @JsonBackReference
    private Collection<Category> categories;
}
