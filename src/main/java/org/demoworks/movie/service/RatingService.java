package org.demoworks.movie.service;

import org.demoworks.movie.model.Film;
import org.demoworks.movie.model.Rating;
import org.demoworks.movie.util.JWTUtil;
import org.demoworks.movie.model.User;
import org.demoworks.movie.repository.RatingRepository;
import org.demoworks.movie.web.dto.RatingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class RatingService {

        private final JWTUtil jwtUtil;
        private final RatingRepository ratingRepository;
        private final FilmService filmService;
        private final UserService userService;


        @Autowired
        public RatingService(final RatingRepository ratingRepository, FilmService filmService, JWTUtil jwtUtil, UserService userService) {
            this.ratingRepository = ratingRepository;
            this.filmService = filmService;
            this.jwtUtil = jwtUtil;
            this.userService = userService;
        }

        @Transactional
        public RatingDto rate(Integer id, String token, RatingDto ratingDto){
            User user = userService.findByUsername(jwtUtil.getUsernameFromToken(token));
            Film film = filmService.getFilmById(id);

            if(ratingRepository.existsByFilmAndUser(film, user)){
                Rating rating = ratingRepository.findByFilmAndAndUser(film,user);
                rating.setRating(ratingDto.getRating());
                return RatingDto.fromEntity(ratingRepository.save(rating));
            }
            return RatingDto.fromEntity(ratingRepository.save(RatingDto.toEntity(film, user, ratingDto.getRating())));
        }




}
