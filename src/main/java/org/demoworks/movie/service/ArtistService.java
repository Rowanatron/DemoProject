package org.demoworks.movie.service;

import org.demoworks.movie.exceptions.DuplicateEntryException;
import org.demoworks.movie.exceptions.NotFoundException;
import org.demoworks.movie.model.Artist;
import org.demoworks.movie.repository.ArtistRepository;
import org.demoworks.movie.web.dto.ArtistDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ArtistService {

    private final ArtistRepository artistRepository;

    @Autowired
    public ArtistService(final ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }


    public ArtistDto create(ArtistDto artistDto) {
        if (artistRepository.existsArtistByNameAndDateOfBirth(artistDto.getName(), artistDto.getDateOfBirth())) {
            throw new DuplicateEntryException("Duplicate Artist!");
        }
        return ArtistDto.fromEntity(artistRepository.save(new Artist(artistDto.getId(), artistDto.getName(), artistDto.getDateOfBirth())));

    }

    public ArtistDto getArtistById(Integer id) {
        return ArtistDto.fromEntity(artistRepository.findById(id).orElseThrow(() -> new NotFoundException("Artist not Found")));
    }

}