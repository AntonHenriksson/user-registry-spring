package se.anton.userregistryspring.app.dto;


//todo add validation

public record AppUserRequest(
        String email,
        String password,
        String firstName,
        String lastName,
        Float funds
) {

}
