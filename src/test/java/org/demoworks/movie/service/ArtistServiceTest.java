package org.demoworks.movie.service;

import org.demoworks.movie.exceptions.DuplicateEntryException;
import org.demoworks.movie.model.Artist;
import org.demoworks.movie.repository.ArtistRepository;
import org.demoworks.movie.web.dto.ArtistDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ArtistServiceTest {

    @InjectMocks
    private ArtistService artistService;
    @Mock
    private ArtistRepository artistRepository;

    private Artist artist;

    @Before
    public void setUp() {

        artist = new Artist(1, "Spielberg", LocalDate.of(1988, 1, 26), null);
    }

    @Test
    public void create_ShouldVerifyCorrectMethod_WasCalledWithCorrectArguments() {

        ArtistDto artistDto = ArtistDto.fromEntity(artist);

        when(artistRepository.save(artist)).thenReturn(artist);
        when(artistRepository.existsArtistByNameAndDateOfBirth(artistDto.getName(), artistDto.getDateOfBirth())).thenReturn(false);

        artistService.create(artistDto);

        Mockito.verify(artistRepository, Mockito.times(1)).save(artist);
        Mockito.verify(artistRepository, Mockito.times(1)).existsArtistByNameAndDateOfBirth(artistDto.getName(), artistDto.getDateOfBirth());
    }

    @Test(expected = DuplicateEntryException.class)
    public void create_ShouldThrowException_IfArtistAlreadyExists() {

        ArtistDto artistDto = ArtistDto.fromEntity(artist);

        when(artistRepository.existsArtistByNameAndDateOfBirth(artistDto.getName(), artistDto.getDateOfBirth())).thenReturn(true);

        artistService.create(artistDto);

        Mockito.verify(artistRepository, Mockito.times(1)).existsArtistByNameAndDateOfBirth(artistDto.getName(), artistDto.getDateOfBirth());
    }

}