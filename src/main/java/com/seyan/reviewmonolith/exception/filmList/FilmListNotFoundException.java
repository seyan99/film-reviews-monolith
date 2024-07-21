package com.seyan.reviewmonolith.exception.filmList;

import java.io.Serial;

public class FilmListNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 9;

    public FilmListNotFoundException(String message) {
        super(message);
    }
}
