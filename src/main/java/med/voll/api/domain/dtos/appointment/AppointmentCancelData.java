package med.voll.api.domain.dtos.appointment;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.models.AppointmentCancelReason;

public record AppointmentCancelData(
        @NotNull
        @JsonAlias("appointment")
        Long id,
        @NotNull
        AppointmentCancelReason reason
) {
}
