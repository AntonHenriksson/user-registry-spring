package se.anton.userregistryspring.app.dto;

public record AppUserResponse(
        String id,
        String email,
        String firstName,
        String lastName,
        Float funds
) {
}
