package se.anton.userregistryspring.app.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.anton.userregistryspring.app.dto.appuser.AppUserResponse;
import se.anton.userregistryspring.app.service.AppUserService;

@RestController
@RequestMapping("/api/users")
public class AppUserController {
    AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppUserResponse> getAppUser(@PathVariable String id) {
        return ResponseEntity.ok(appUserService.getById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAppUser(@PathVariable String id) {
        appUserService.deleteAppUser(id);
        return ResponseEntity.noContent().build();
    }
}
