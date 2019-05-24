package org.demoworks.movie.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class FilmTest {

    private Film film;

    @Before
    public void setUp(){
        film = new Film();

    }

    @Test
    public void getAverageRating_ShouldReturn_5_5() {
        Rating rating1 = new Rating();
        Rating rating2 = new Rating();

        rating1.setRating(5.0);
        rating2.setRating(6.0);

        film = Film.FilmBuilder
                .newInstance()
                .rated(Arrays.asList(rating1, rating2))
                .build();


        Assert.assertEquals(5.5,this.film.getAverageRating(),0);
    }

    @Test
    public void getAverageRating_ShouldReturn_MinusOne_IfRatingsIsEmpty() {
        Assert.assertEquals(-1.0,film.getAverageRating(),0);

    }
}