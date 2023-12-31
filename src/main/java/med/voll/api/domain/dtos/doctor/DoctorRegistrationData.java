package med.voll.api.domain.dtos.doctor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.models.Specialty;
import med.voll.api.domain.dtos.AddressData;

public record DoctorRegistrationData(
        @NotBlank
        String name,

        @NotBlank
        @Email
        String email,

        @NotBlank @Pattern(regexp = "\\d{9,11}")
        String phone,

        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,

        @NotNull
        Specialty specialty,

        @NotNull
        @Valid
        AddressData address) {
}
