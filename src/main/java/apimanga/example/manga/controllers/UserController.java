package apimanga.example.manga.controllers;

import apimanga.example.manga.dtos.CreateUser;
import apimanga.example.manga.dtos.ErrorData;
import apimanga.example.manga.models.User;
import apimanga.example.manga.repositories.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/users")
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity getUsers(){
        var userList = userRepository.findAll();
        return ResponseEntity.ok().body(userList);
    }

    @PostMapping
    @Transactional
    public ResponseEntity createUser(@RequestBody CreateUser data){
        if (userRepository.existsByEmail(data.email())){
            return ResponseEntity.badRequest().body(new ErrorData("Error", "Email já cadastrado"));

        }

//        if (!DataBase.passwordUser(data.password(), data.repassword())){
//            return ResponseEntity.badRequest().body(new ErrorData("As senhas devem ser iguais."));
//        }


        var user = new User(
                data.email(),
                passwordEncoder.encode(data.password())
        );

        userRepository.save(user);

        return ResponseEntity.ok().body("Conta criada com sucesso!");
    }


    @PostMapping("/login/{email}/{password}")
    public ResponseEntity login(@PathVariable @Valid String email, @PathVariable @Valid String password){
        try {
            var user = userRepository.getReferenceByEmail(email);
            if(user.getEmail().equals(email) && user.getPassword().equals(password)){
                return ResponseEntity.ok().body(user);
            }
            return ResponseEntity.badRequest().body(new ErrorData("Error", "E-mail ou senha inválidos."));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorData("Error", "Login inválido."));
        }
    }
}
