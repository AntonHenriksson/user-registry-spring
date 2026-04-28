package se.anton.userregistryspring.app.dto.appuser;

import java.math.BigDecimal;

public record AppUserResponse(
        String id,
        String email,
        String firstName,
        String lastName,
        BigDecimal funds,
        String roles
) {
}
