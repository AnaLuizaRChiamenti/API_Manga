package apimanga.example.manga.repositories;

import apimanga.example.manga.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByEmail(String email);

    User getReferenceByEmail(String email);

    UserDetails findByEmail(String email);  
}
