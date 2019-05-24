package org.demoworks.movie.web.controller;

import org.demoworks.movie.service.FilmService;
import org.demoworks.movie.service.RatingService;
import org.demoworks.movie.service.ReviewService;
import org.demoworks.movie.web.dto.FilmDto;
import org.demoworks.movie.web.dto.RatingDto;
import org.demoworks.movie.web.dto.ReviewDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(path = "/films")
public class FilmRestController {

    private final FilmService filmService;
    private final ReviewService reviewService;
    private final RatingService ratingService;

    @Autowired
    public FilmRestController(final FilmService filmService, final ReviewService reviewService, final RatingService ratingService) {
        this.filmService = filmService;
        this.reviewService = reviewService;
        this.ratingService = ratingService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FilmDto create(@RequestBody FilmDto filmDto) {
        return filmService.create(filmDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<FilmDto> getAll() {
        return filmService.fetchAllFilms();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FilmDto getFilmById(@PathVariable("id") Integer id) {
        return filmService.getFilmDtoById(id);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public FilmDto updateFilmByID(@PathVariable("id") Integer id, @RequestBody FilmDto filmDto) {
        return filmService.updateFilmById(id, filmDto);
    }

    @PostMapping("/{id}/reviews")
    @ResponseStatus(HttpStatus.CREATED)
    public ReviewDto addReview(@PathVariable Integer id, @RequestBody ReviewDto reviewDto, @RequestHeader(value = "Authorization") String token) {
        return reviewService.create(id, token, reviewDto);
    }

    @PostMapping("/{id}/ratings")
    @ResponseStatus(HttpStatus.CREATED)
    public RatingDto addRating(@PathVariable Integer id, @RequestBody RatingDto ratingDto, @RequestHeader(value = "Authorization") String token) {
        return ratingService.rate(id, token, ratingDto);
    }


}

