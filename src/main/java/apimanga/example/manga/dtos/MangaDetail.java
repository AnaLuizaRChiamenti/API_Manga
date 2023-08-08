package apimanga.example.manga.dtos;

import apimanga.example.manga.enums.Category;
import apimanga.example.manga.models.Manga;
import lombok.Getter;
import java.util.UUID;

public record MangaDetail(
        UUID id,
        String title,
        String description,
        Boolean read,

        Category category
) {
    public MangaDetail(Manga manga) {
        this(manga.getId(), manga.getTitle(), manga.getDescription(), manga.getRead(), manga.getCategory());
    }

}