package med.voll.api.domain.dtos.appointment;

import med.voll.api.domain.models.Appointment;

import java.time.LocalDateTime;

public record AppointmentDetailingData(Long id, Long doctorId, Long patientId, LocalDateTime date) {
    public AppointmentDetailingData(Appointment appointment) {
        this(appointment.getId(), appointment.getDoctor().getId(), appointment.getPatient().getId(), appointment.getDate());
    }
}
