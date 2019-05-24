package org.demoworks.movie.repository;

import org.demoworks.movie.model.Film;
import org.demoworks.movie.model.Rating;
import org.demoworks.movie.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends CrudRepository<Rating, Integer> {

    boolean existsByFilmAndUser(Film film, User user);

    Rating findByFilmAndAndUser(Film film, User user);
}
