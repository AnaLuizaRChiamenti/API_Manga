package apimanga.example.manga.dtos;

import apimanga.example.manga.models.Manga;
import apimanga.example.manga.models.User;

import java.util.List;

public record UserDetail(
        String email,
        String password,
        List<Manga> mangas
) {
    public UserDetail(User U) {
        this(
                U.getEmail(),
                U.getPassword(),
                U.getMangas()
        );

    }

}