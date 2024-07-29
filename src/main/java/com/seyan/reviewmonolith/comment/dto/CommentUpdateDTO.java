package com.seyan.reviewmonolith.comment.dto;

import jakarta.validation.constraints.NotNull;

public record CommentUpdateDTO(
        @NotNull(message = "Content should not be null")
        String content
) {
}
