package se.anton.userregistryspring.app.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @Email(message = "Use the same email that you registered with")
        String email,
        @NotBlank(message = "Password is mandatory")
        String password
) {
}
