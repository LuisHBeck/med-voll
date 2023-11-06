package med.voll.api.domain.dtos.user;

import jakarta.validation.constraints.NotBlank;

public record AuthData(
        @NotBlank
        String username,
        @NotBlank
        String password
) {
}
