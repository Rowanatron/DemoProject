package org.demoworks.movie.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;


@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "artist")
public class Artist {

    public Artist(String name, LocalDate dateOfBirth){
        this.name =  name;
        this.dateOfBirth = dateOfBirth;
    }



    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "artist_id")
    private Integer id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @OneToMany (mappedBy = "director", fetch = FetchType.LAZY)
    private Collection<Film> works;

    public Artist(Integer id, String name, LocalDate dateOfBirth) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }


    public Collection<String> getTitlesofWorks (){
        return this.works.stream().map(Film::getTitle).collect(Collectors.toList());

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artist artist = (Artist) o;
        return Objects.equals(id, artist.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
