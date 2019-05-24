package org.demoworks.movie.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ArtistTest {

    Film film1;
    Film film2;
    Film film3;
    Artist director;

    @Before
    public void setUp() {
        film1 = Film
                .FilmBuilder
                .newInstance()
                .named("My Debut")
                .build();

        film2 = Film
                .FilmBuilder
                .newInstance()
                .named("Going Downhill")
                .build();

        film3 = Film
                .FilmBuilder
                .newInstance()
                .named("The End of the Line")
                .build();

        director = new Artist();
        director.setWorks(Arrays.asList(film1, film2, film3));
    }

    @Test
    public void getTitlesofWorks_ThreeTitles_Test() {

        Assert.assertTrue((director.getTitlesofWorks().containsAll(Arrays.asList("My Debut", "Going Downhill", "The End of the Line"))));
        Assert.assertEquals(3, director.getTitlesofWorks().size());


    }
}