package med.voll.api.domain.dtos.user;

import jakarta.validation.constraints.NotBlank;

public record UserData(
        @NotBlank
        String username,
        @NotBlank
        String password
) {
}
