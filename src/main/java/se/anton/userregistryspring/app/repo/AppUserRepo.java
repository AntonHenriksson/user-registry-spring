package se.anton.userregistryspring.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import se.anton.userregistryspring.app.domain.AppUser;

public interface AppUserRepo extends JpaRepository<AppUser, String> {
}
