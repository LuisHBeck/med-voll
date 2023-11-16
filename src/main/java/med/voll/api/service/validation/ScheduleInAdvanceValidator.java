package med.voll.api.service.validation;

import med.voll.api.domain.dtos.appointment.AppointmentScheduleData;
import med.voll.api.infra.exception.ValidException;

import java.time.Duration;
import java.time.LocalDateTime;

public class ScheduleInAdvanceValidator {
    public void valid(AppointmentScheduleData data) {
        var appointmentDate = data.date();
        var now = LocalDateTime.now();
        var differenceInMinutes = Duration.between(now, appointmentDate).toMinutes();
        if(differenceInMinutes < 30) throw new ValidException("Appointment must be scheduled at least 30 minutes in advance");
    }
}
