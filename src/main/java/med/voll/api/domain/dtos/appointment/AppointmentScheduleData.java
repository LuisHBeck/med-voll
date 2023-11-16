package med.voll.api.domain.dtos.appointment;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.models.Specialty;

import java.time.LocalDateTime;

public record AppointmentScheduleData(
        @JsonAlias({"doctor", "doctor_id"})
        Long doctorId,

        Specialty specialty,

        @NotNull
        @JsonAlias({"patient", "patient_id"})
        Long patientId,
        @NotNull
        @Future
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime date
) {
}
