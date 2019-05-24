package org.demoworks.movie.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.demoworks.movie.model.Film;
import org.demoworks.movie.model.Review;
import org.demoworks.movie.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ReviewDto {

    private FilmDto filmDto;
    private UserDto reviewer;
    private String review;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate submitted;
    @JsonFormat(pattern = "mm-hh dd-MM-yyyy")
    private LocalDateTime lastEdited;


    public ReviewDto fromEntity(Review review) {
        return new ReviewDto(FilmDto.from(review.getFilm()), UserDto.fromEntity(review.getReviewer()), review.getReview(), review.getSubmitted(), review.getLastEdited());

    }

    public Review toEntity(Film film, User user, ReviewDto reviewDto) {
        return new Review(film, user, reviewDto.getReview(), LocalDate.now(), LocalDateTime.now());
    }

}
