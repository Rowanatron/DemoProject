package org.demoworks.movie.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateEntryException extends RuntimeException {

    public DuplicateEntryException(String errMessage){
        super(errMessage);
    }

}
