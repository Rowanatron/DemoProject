package org.demoworks.movie.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.demoworks.movie.model.Artist;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Objects;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArtistDto {

        private String name;
        @JsonFormat(pattern = "dd-MM-yyyy")
        private LocalDate dateOfBirth;
        private Integer id;

        public static ArtistDto fromEntity(Artist artist) {
                return new ArtistDto(artist.getName(), artist.getDateOfBirth(), artist.getId());
        }

        public static Artist toEntity(ArtistDto artistDto) {
                return new Artist(artistDto.getId(), artistDto.getName(), artistDto.getDateOfBirth(), Collections.emptyList());
        }


        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                ArtistDto artistDto = (ArtistDto) o;
                return id.equals(artistDto.id);
        }

        @Override
        public int hashCode() {
                return Objects.hash(id);
        }
}
