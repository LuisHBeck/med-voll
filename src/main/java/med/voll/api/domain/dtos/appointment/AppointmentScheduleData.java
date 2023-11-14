package med.voll.api.domain.dtos.appointment;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AppointmentScheduleData(
        @JsonAlias({"doctor", "doctor_id"})
        Long doctorId,

        @NotNull
        @JsonAlias({"patient", "patient_id"})
        Long patientId,
        @NotNull
        @Future
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime date
) {
}
