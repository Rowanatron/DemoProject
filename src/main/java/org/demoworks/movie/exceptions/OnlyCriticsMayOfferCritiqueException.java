package org.demoworks.movie.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class OnlyCriticsMayOfferCritiqueException extends RuntimeException {

    public OnlyCriticsMayOfferCritiqueException() {
        super("You must be a critic to submit a review");
    }
}
