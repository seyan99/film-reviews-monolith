package com.seyan.reviewmonolith.exception.film;

import java.io.Serial;

public class IncorrectDateRangeException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 2;

    public IncorrectDateRangeException(String message) {
        super(message);
    }
}
