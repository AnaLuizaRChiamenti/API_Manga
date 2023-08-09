package apimanga.example.manga.models;

import apimanga.example.manga.dtos.AddManga;
import apimanga.example.manga.dtos.UpdateManga;
import apimanga.example.manga.enums.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
@Entity
@Table(name = "mangas")
@AllArgsConstructor
@NoArgsConstructor

public class Manga {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    private String description;
    private Boolean read;
    private String emailuser;

    @Enumerated(EnumType.STRING)
    private Category category;
    public Manga(AddManga newManga, String emailUser) {
        title = newManga.title();
        description = newManga.description();
        read = newManga.read();
        emailuser = newManga.emailUser();
        category = newManga.category();
    }

    public void UpdateManga(UpdateManga mangaUpdated) {
        if(mangaUpdated.title() != null) {
            title = mangaUpdated.title();
        }
        if(mangaUpdated.description() != null) {
            description = mangaUpdated.description();
        }
        if(mangaUpdated.category() != null) {
            category = mangaUpdated.category();
        }
        if(mangaUpdated.read() != null) {
            read = mangaUpdated.read();
        }
    }
}

