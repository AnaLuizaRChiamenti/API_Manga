package apimanga.example.manga.dtos;

import apimanga.example.manga.enums.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateManga(
        @NotBlank
        @NotNull
        String title,
        String description,

        @NotNull
        Category category,

        Boolean read

) {
}
