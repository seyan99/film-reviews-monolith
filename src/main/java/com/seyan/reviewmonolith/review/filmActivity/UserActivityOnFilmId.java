package com.seyan.reviewmonolith.review.filmActivity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@ToString
@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class UserActivityOnFilmId implements Serializable {
    private Long userId;
    private Long filmId;
}
