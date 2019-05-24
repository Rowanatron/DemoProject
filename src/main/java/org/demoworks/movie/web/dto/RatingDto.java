package org.demoworks.movie.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.demoworks.movie.model.Film;
import org.demoworks.movie.model.Rating;
import org.demoworks.movie.model.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingDto {

    private Integer id;
    private FilmDto film;
    private UserDto user;
    private double rating;

    public static Rating toEntity(Film film, User user, double rating){
        return new Rating(film, user, rating);
    }

    public static RatingDto fromEntity(Rating rating){
        return new RatingDto(rating.getId(), FilmDto.from(rating.getFilm()), UserDto.fromEntity(rating.getUser()), rating.getRating());
    }

}
