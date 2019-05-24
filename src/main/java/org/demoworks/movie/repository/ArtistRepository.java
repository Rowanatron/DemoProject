package org.demoworks.movie.repository;

import org.demoworks.movie.model.Artist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ArtistRepository extends CrudRepository<Artist, Integer> {

    boolean existsArtistByNameAndDateOfBirth(String name, LocalDate dateOfBirth);

    Optional<Artist> findById(Integer id);

}
