package se.anton.userregistryspring.app.mapper;

import se.anton.userregistryspring.app.domain.AppUser;
import se.anton.userregistryspring.app.dto.AppUserRequest;
import se.anton.userregistryspring.app.dto.AppUserResponse;

public class AppUserMapper {

    public static AppUser toEntity(AppUserRequest request) {
        AppUser appUser = new AppUser();
        appUser.setEmail(request.email());
        appUser.setFirstName(request.firstName());
        appUser.setLastName(request.lastName());
        appUser.setFunds(request.funds());
        return appUser;
    }

    public static AppUserResponse toResponse(AppUser appUser) {
        return new AppUserResponse(
                appUser.getId(),
                appUser.getEmail(),
                appUser.getFirstName(),
                appUser.getLastName(),
                appUser.getFunds()
        );
    }

}
