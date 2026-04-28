package se.anton.userregistryspring.app.dto.appuser;


//todo add validation

import java.math.BigDecimal;

public record AppUserRequest(
        String email,
        String password,
        String firstName,
        String lastName,
        BigDecimal funds
) {

}
