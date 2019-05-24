package org.demoworks.movie.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.demoworks.movie.model.Artist;
import org.demoworks.movie.model.Film;
import org.demoworks.movie.model.Film.FilmBuilder;
import org.demoworks.movie.model.enums.Genre;

import java.time.LocalDateTime;
import java.util.Objects;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class FilmDto {

    private Integer id;
    private String title;
    private ArtistDto director;
    private Integer year;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Enum<Genre> genre;
    private LocalDateTime fetchedAt;

    public static FilmDto from(Film film) {
        FilmDto filmDto = new FilmDto();
        filmDto.id = film.getId();
        filmDto.title = film.getTitle();
        filmDto.year = film.getYear();
        filmDto.genre = film.getGenre();
        filmDto.fetchedAt = LocalDateTime.now();
        if (Objects.nonNull(film.getDirector())) {
            filmDto.director = ArtistDto.fromEntity(film.getDirector());
        }
        return filmDto;
    }


    public static Film toEntity(Integer id, FilmDto filmDto, Artist artist) {
        return FilmBuilder
                .newInstance()
                .identifiedBy(id)
                .directedBy(artist)
                .genre(filmDto.getGenre())
                .releasedAtYear(filmDto.getYear())
                .named(filmDto.getTitle())
                .build();
    }

    public static Film toEntity(FilmDto filmDto, Artist artist) {
        return FilmBuilder
                .newInstance()
                .directedBy(artist)
                .genre(filmDto.getGenre())
                .releasedAtYear(filmDto.getYear())
                .named(filmDto.getTitle())
                .build();
    }

}