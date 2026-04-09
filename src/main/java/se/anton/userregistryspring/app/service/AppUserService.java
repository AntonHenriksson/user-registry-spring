package se.anton.userregistryspring.app.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import se.anton.userregistryspring.app.domain.AppUser;
import se.anton.userregistryspring.app.dto.AppUserRequest;
import se.anton.userregistryspring.app.dto.AppUserResponse;
import se.anton.userregistryspring.app.mapper.AppUserMapper;
import se.anton.userregistryspring.app.repo.AppUserRepo;


@Service
public class AppUserService {
    AppUserRepo appUserRepo;

    public AppUserService(AppUserRepo appUserRepo) {
        this.appUserRepo = appUserRepo;
    }

    @Transactional
    public AppUserResponse saveAppUser(AppUserRequest request) {
        AppUser appUser = AppUserMapper.toEntity(request);
        //todo add password encoding here
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
