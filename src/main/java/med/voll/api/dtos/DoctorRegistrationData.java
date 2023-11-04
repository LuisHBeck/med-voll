package med.voll.api.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.doctor.Specialty;

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
