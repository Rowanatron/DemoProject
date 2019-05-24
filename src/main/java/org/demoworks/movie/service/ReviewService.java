package org.demoworks.movie.service;


import org.demoworks.movie.exceptions.OnlyCriticsMayOfferCritiqueException;
import org.demoworks.movie.model.User;
import org.demoworks.movie.util.JWTUtil;
import org.demoworks.movie.repository.ReviewRepository;
import org.demoworks.movie.web.dto.ReviewDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private final FilmService filmService;
    private final UserService userService;
    private final JWTUtil jwtUtil;
    private final ReviewRepository reviewRepository;


    @Autowired
    public ReviewService(JWTUtil jwtUtil, UserService userService, FilmService filmService, final ReviewRepository reviewRepository) {
        this.jwtUtil = jwtUtil;
        this.reviewRepository = reviewRepository;
        this.filmService = filmService;
        this.userService = userService;
    }

    public ReviewDto create(Integer filmId, String token, ReviewDto reviewDto) {
        User user = userService.findByUsername(jwtUtil.getUsernameFromToken(token));
        if (!user.isCritic()) {
            throw new OnlyCriticsMayOfferCritiqueException();
        }
        return reviewDto.fromEntity(reviewRepository.save(reviewDto.toEntity(filmService.getFilmById(filmId), user, reviewDto)));
    }

}
