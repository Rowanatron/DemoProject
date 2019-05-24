package org.demoworks.movie.service;

import org.demoworks.movie.exceptions.DuplicateEntryException;
import org.demoworks.movie.exceptions.FilmNotFoundException;
import org.demoworks.movie.model.Film;
import org.demoworks.movie.repository.FilmRepository;
import org.demoworks.movie.web.dto.ArtistDto;
import org.demoworks.movie.web.dto.FilmDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class FilmService {

    private final FilmRepository filmRepository;
    private final ArtistService artistService;


    @Autowired
    public FilmService(final FilmRepository filmRepository, ArtistService artistService) {
        this.filmRepository = filmRepository;
        this.artistService = artistService;
    }


    public FilmDto create(FilmDto filmDto) {
        if (filmRepository.existsByTitleAndDirectorAndYear(filmDto.getTitle(), ArtistDto.toEntity(artistService.getArtistById(filmDto.getDirector().getId())), filmDto.getYear())) {
            throw new DuplicateEntryException("Duplicate Film!");
        }
        return FilmDto.from
                (filmRepository.save(FilmDto.toEntity(filmDto,
                        ArtistDto.toEntity(artistService.getArtistById(filmDto.getDirector().getId()
                        ))
                )));

    }

    public Iterable<FilmDto> fetchAllFilms() {
        return StreamSupport
                .stream(filmRepository.findAll().spliterator(), true)
                .map(FilmDto::from)
                .collect(Collectors.toList());
    }

    public FilmDto getFilmDtoById(Integer id) {
        return FilmDto.from(filmRepository.findById(id).orElseThrow(FilmNotFoundException::new));
    }

    public Film getFilmById(Integer id) {
        return filmRepository.findById(id).orElseThrow(FilmNotFoundException::new);
    }

    @Transactional
    public FilmDto updateFilmById(Integer id, FilmDto filmDto) {
        return FilmDto.from
                (filmRepository.save(FilmDto.toEntity(id, filmDto,
                        ArtistDto.toEntity(artistService.getArtistById(filmDto.getDirector().getId()))
                )));
    }


}