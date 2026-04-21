package se.anton.userregistryspring.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import se.anton.userregistryspring.app.domain.AppUser;

import java.util.Optional;

public interface AppUserRepo extends JpaRepository<AppUser, String> {

    Optional<AppUser> findByEmail(String email);
}
