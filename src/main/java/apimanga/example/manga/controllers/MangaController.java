package apimanga.example.manga.controllers;

import apimanga.example.manga.dtos.AddManga;
import apimanga.example.manga.dtos.ErrorData;
import apimanga.example.manga.dtos.MangaDetail;
import apimanga.example.manga.enums.Category;
import apimanga.example.manga.models.Manga;
import apimanga.example.manga.repositories.UserMangaRepository;
import apimanga.example.manga.repositories.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/mangas")

public class MangaController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMangaRepository userTaskRepository;

    @PostMapping("/{email}")
    @Transactional
    public ResponseEntity addManga(@PathVariable String email, @RequestBody @Valid AddManga newManga, String emailUser){
        var userEmail = userRepository.findById(newManga.emailUser());

        if(userEmail.isEmpty()) {
            return ResponseEntity.badRequest().body(new ErrorData("Usuário não localizado."));
        }

        var user = userEmail.get();

        var manga = new Manga(newManga, user.getEmail());
        userTaskRepository.save(manga);
        return ResponseEntity.ok().body(newManga);
    }

    @GetMapping("/{email}")
    public ResponseEntity getMangas(@PathVariable String email, @RequestParam(required = false) String title, @RequestParam(required = false) boolean archived, @RequestParam(required = false) Category category){
        var checkUser = userRepository.getReferenceById(email);

        var mangas = checkUser.getMangas();

        if(mangas == null) {
            return ResponseEntity.badRequest().body(new ErrorData("Nenhum manga adicionado."));
        }

        if(title != null) {
            mangas = mangas.stream().filter(t -> t.getTitle().contains((title))).toList();
            return ResponseEntity.ok().body(mangas);
        }

        if(archived) {
            mangas = mangas.stream().filter(a -> a.getRead().equals(true)).toList();
            return ResponseEntity.ok().body(mangas);
        }

        if (category != null) {
            mangas = mangas.stream().filter(m -> m.getCategory() == category).toList();
        }

        return  ResponseEntity.ok().body(mangas.stream().map(MangaDetail::new).toList());

    }
}
