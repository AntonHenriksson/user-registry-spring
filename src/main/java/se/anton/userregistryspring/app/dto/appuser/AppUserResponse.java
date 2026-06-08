package se.anton.userregistryspring.app.dto.appuser;

public record AppUserResponse(
        String id,
        String email,
        String firstName,
        String lastName,
        String roles
) {
}
