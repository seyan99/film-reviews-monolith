package com.seyan.reviewmonolith.film.dto;

import com.seyan.reviewmonolith.film.Genre;
import com.seyan.reviewmonolith.profile.dto.ProfileInFilmResponseDTO;

import java.time.LocalDate;
import java.util.List;

public record FilmResponseDTO(
        Long id,
        String title,
        String description,
        LocalDate releaseDate,
        String filmUrl,
        Double rating,
        ProfileInFilmResponseDTO director,
        List<ProfileInFilmResponseDTO> cast,
        Genre genre,
        Integer runningTimeMinutes,
        Long watchedCount,
        Long listCount,
        Long likeCount
) {

}
