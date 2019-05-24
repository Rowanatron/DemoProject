package org.demoworks.movie.service;

import org.demoworks.movie.model.Film;
import org.demoworks.movie.model.Rating;
import org.demoworks.movie.model.User;
import org.demoworks.movie.repository.RatingRepository;
import org.demoworks.movie.util.JWTUtil;
import org.demoworks.movie.web.dto.FilmDto;
import org.demoworks.movie.web.dto.RatingDto;
import org.demoworks.movie.web.dto.UserDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.when;

import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class RatingServiceTest {

    @InjectMocks
    RatingService ratingService;
    @Mock
    JWTUtil jwtUtil;
    @Mock
    RatingRepository ratingRepository;
    @Mock
    FilmService filmService;
    @Mock
    UserService userService;


    @Before
    public void setUp() {

    }


    @Test
    public void rate_Test() {

        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6IlJvd2FuIn0.eFkl6rQJ9vY7MoKOiuNhq28cuo0vq38XAl2202j95L8";

        Film film = Film.FilmBuilder
                .newInstance()
                .identifiedBy(1)
                .build();

        User user = new User();
        user.setId(2);
        user.setUsername("Rowan");

        Rating rating = new Rating(film, user, 5.0);

        when(ratingRepository.save(Mockito.any(Rating.class))).thenReturn(rating);

        RatingDto ratingDto = new RatingDto();
        ratingDto.setRating(5.0);

        RatingDto ratingDtoReturn = new RatingDto(null, FilmDto.from(film), UserDto.fromEntity(user), 5.0);

        Assert.assertEquals(ratingDtoReturn.getRating(), ratingService.rate(1, token, ratingDto).getRating(), 0);
        Assert.assertEquals(ratingDtoReturn.getUser().getUsername(), ratingService.rate(1, token, ratingDto).getUser().getUsername());
        Assert.assertEquals(1, ratingService.rate(1, token, ratingDto).getFilm().getId(), 0);

    }
}