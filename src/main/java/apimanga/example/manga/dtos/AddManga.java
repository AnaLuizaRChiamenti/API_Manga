package apimanga.example.manga.dtos;

import apimanga.example.manga.enums.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddManga(
        @NotBlank
        @NotNull
        String title,
        String description,
        @NotNull
        String emailUser,

        @NotNull
        Category category,

        Boolean read
) {
}
