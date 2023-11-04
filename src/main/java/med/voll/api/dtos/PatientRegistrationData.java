package med.voll.api.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CPF;

public record PatientRegistrationData(
        @NotBlank
        String name,

        @NotBlank
        @Email
        String email,

        @NotBlank @Pattern(regexp = "\\d{9,11}")
        String phone,

        @NotBlank
        @CPF
        String cpf,

        @NotNull @Valid
        AddressData address
) {
}
