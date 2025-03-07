package com.seyan.reviewmonolith.film.log.dto;

import com.seyan.reviewmonolith.film.log.ActivityOnFilmId;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;

public record ActivityOnFilmRequest(
        @NotNull
        ActivityOnFilmId id,
        @Max(value = 5)
        Double rating,
        Boolean isWatched,
        Boolean isLiked,
        Boolean isInWatchlist,
        Boolean hasReview,
        String reviewContent
) {

}
