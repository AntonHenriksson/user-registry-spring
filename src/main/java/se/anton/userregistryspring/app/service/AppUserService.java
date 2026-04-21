package se.anton.userregistryspring.app.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.anton.userregistryspring.app.domain.AppUser;
import se.anton.userregistryspring.app.dto.appuser.AppUserRequest;
import se.anton.userregistryspring.app.dto.appuser.AppUserResponse;
import se.anton.userregistryspring.app.mapper.AppUserMapper;
import se.anton.userregistryspring.app.repo.AppUserRepo;


@Service
public class AppUserService {
    private final AppUserRepo appUserRepo;
    private final PasswordEncoder passwordEncoder;

    public AppUserService(AppUserRepo appUserRepo, PasswordEncoder passwordEncoder) {
        this.appUserRepo = appUserRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public AppUserResponse saveAppUser(AppUserRequest request) {
        AppUser appUser = AppUserMapper.toEntity(request);
        appUser.setPassword(passwordEncoder.encode(request.password()));
        appUserRepo.save(appUser);
        return AppUserMapper.toResponse(appUser);
    }

    @Transactional
    public void deleteAppUser(String id) {
        AppUser appUser = appUserRepo.
                findById(id).orElseThrow(() -> new EntityNotFoundException(id));
        appUserRepo.delete(appUser);
    }

    public AppUserResponse getById(String id) {
        return AppUserMapper.toResponse(appUserRepo.
                findById(id).orElseThrow(() -> new EntityNotFoundException(id)));
    }

}
