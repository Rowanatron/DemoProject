package org.demoworks.movie.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.demoworks.movie.model.enums.Genre;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "film")
public class Film implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "film_id")
    private Integer id;
    @Column(name = "title", nullable = false)
    private String title;
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id")
    private Artist director;
    @Column(name = "year")
    private int year;
    @Column(name = "genre")
    private Enum<Genre> genre;
    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<Rating> ratings;
    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<Review> reviews;

    private Film(FilmBuilder builder) {
        this(builder.id, builder.title, builder.director, builder.year, builder.genre, builder.userRatings, builder.reviews);
    }

    public double getAverageRating(){
        if (CollectionUtils.isEmpty(this.ratings)) {
            return -1.0;
        }
        return this
                .ratings
                .stream()
                .collect(Collectors.summarizingDouble(Rating::getRating))
                .getAverage();
    }

    @Override
    public int hashCode(){
        return this.year + this.title.length();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Film) {
            if (title.equals(((Film) o).getTitle())
                    && year == ((Film) o).getYear()
                    && director.getName().equals(((Film) o).getDirector().getName())) ;
            {
                return true;
            }
        } else return false;
    }

    public static class FilmBuilder {

        private Integer id;
        private String title;
        private Artist director;
        private int year;
        private Enum<Genre> genre;
        private List<Rating> userRatings;
        private List<Review> reviews;

        private FilmBuilder() {
        }

        public static FilmBuilder newInstance() {
            return new FilmBuilder();
        }

        public FilmBuilder identifiedBy(Integer id) {
            this.id = id;
            return this;
        }

        public FilmBuilder named(String title) {
            this.title = title;
            return this;
        }

        public FilmBuilder directedBy(Artist artist) {
            this.director = artist;
            return this;
        }

        public FilmBuilder releasedAtYear(Integer year) {
            this.year = year;
            return this;
        }

        public FilmBuilder genre(Enum<Genre> genre) {
            this.genre = genre;
            return this;
        }

        public FilmBuilder rated(List<Rating> ratings) {
            this.userRatings = ratings;
            return this;
        }

        public FilmBuilder reviewed(List<Review> reviews) {
            this.reviews = reviews;
            return this;
        }

        public Film build() {
            return new Film(this);
        }
    }
}