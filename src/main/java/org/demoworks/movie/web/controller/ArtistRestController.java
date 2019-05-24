package org.demoworks.movie.web.controller;


import org.demoworks.movie.service.ArtistService;
import org.demoworks.movie.web.dto.ArtistDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/artists")
public class ArtistRestController {

    private final ArtistService artistService;

    @Autowired
    public ArtistRestController(final ArtistService artistService){
        this.artistService = artistService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ArtistDto create(@RequestBody ArtistDto artistDto){
        return artistService.create(artistDto);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ArtistDto getArtistById(@PathVariable("id") Integer id){
        return artistService.getArtistById(id);
    }
}
