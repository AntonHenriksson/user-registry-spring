package se.anton.userregistryspring.service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import se.anton.userregistryspring.app.domain.AppUser;
import se.anton.userregistryspring.app.dto.appuser.AppUserRequest;
import se.anton.userregistryspring.app.dto.appuser.AppUserResponse;
import se.anton.userregistryspring.app.repo.AppUserRepo;
import se.anton.userregistryspring.app.service.AppUserService;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AppUserServiceTest {

    @Mock
    private AppUserRepo appUserRepo;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AppUserService appUserService;


    @Test
    void saveAppUserTest() {

        AppUserRequest request = new AppUserRequest(
                "email@email.se",
                "password",
                "testfirst",
                "testlast",
                BigDecimal.valueOf(30)
        );
        appUserService.saveAppUser(request);

        verify(appUserRepo, times(1)).save(any());
    }

    @Test
    void getUserByIdTest() {

        String userId = UUID.randomUUID().toString();
        AppUser user = new AppUser();
        user.setId(userId);
        user.setEmail("email@email.se");
        user.setFirstName("testfirst");
        user.setLastName("testlast");
        user.setPassword("password");

        when(appUserRepo.findById(userId)).thenReturn(Optional.of(user));

        AppUserResponse response = appUserService.getById(userId);

        assertNotNull(response);
        assertEquals("email@email.se", response.email());
        verify(appUserRepo).findById(userId);

    }


}
