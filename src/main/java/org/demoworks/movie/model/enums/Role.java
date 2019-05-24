package org.demoworks.movie.model.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum  Role {
    ADMIN,CRITIC,USER
}
