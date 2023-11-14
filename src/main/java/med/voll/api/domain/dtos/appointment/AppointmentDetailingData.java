package med.voll.api.domain.dtos.appointment;

import java.time.LocalDateTime;

public record AppointmentDetailingData(Long id, Long doctorId, Long patientId, LocalDateTime date) {
}
