package apimanga.example.manga.repositories;

import apimanga.example.manga.models.Manga;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserMangaRepository  extends JpaRepository<Manga, UUID> {
//    List<Task> getAllTasksUser(String emailuser);
}