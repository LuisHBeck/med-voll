package med.voll.api.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AddressData(
        @NotBlank
        String logradouro,

        @NotBlank
        String neighborhood,

        @NotBlank
        @Pattern(regexp = "\\d{8}")
        String cep,

        @NotBlank
        String city,

        @NotBlank
        String uf,

        String number,
        String complement) {
}
