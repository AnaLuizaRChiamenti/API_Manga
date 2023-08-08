package apimanga.example.manga.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateManga(
        @NotBlank
        @NotNull
        String title,
        String description,
        Boolean read
) {
}
