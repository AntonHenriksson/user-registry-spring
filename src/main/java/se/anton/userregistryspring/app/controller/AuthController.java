package se.anton.userregistryspring.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.anton.userregistryspring.app.domain.AppUser;
import se.anton.userregistryspring.app.dto.appuser.AppUserRequest;
import se.anton.userregistryspring.app.dto.appuser.AppUserResponse;
import se.anton.userregistryspring.app.dto.auth.LoginRequest;
import se.anton.userregistryspring.app.dto.auth.LoginResponse;
import se.anton.userregistryspring.app.repo.AppUserRepo;
import se.anton.userregistryspring.app.security.TokenService;
import se.anton.userregistryspring.app.service.AppUserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final AppUserRepo appUserRepo;
    private final AppUserService appUserService;

    public AuthController(TokenService tokenService, AuthenticationManager authenticationManager, AppUserRepo appUserRepo, AppUserService appUserService) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
        this.appUserRepo = appUserRepo;
        this.appUserService = appUserService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.email(), loginRequest.password()
                )
        );
        AppUser appUser = appUserRepo.findByEmail(authentication.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        String token = tokenService.generateToken(appUser.getEmail(), authentication);

        return ResponseEntity.ok(new LoginResponse(token));
    }


    @PostMapping("/register")
    public ResponseEntity<AppUserResponse> register(@RequestBody AppUserRequest appUserRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(appUserService.saveAppUser(appUserRequest));
    }


}
