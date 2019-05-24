package org.demoworks.movie.repository;

import org.demoworks.movie.model.Artist;
import org.demoworks.movie.model.Film;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FilmRepository extends CrudRepository<Film, Integer> {

    boolean existsByTitleAndDirectorAndYear (String title, Artist artist, int Year);

}