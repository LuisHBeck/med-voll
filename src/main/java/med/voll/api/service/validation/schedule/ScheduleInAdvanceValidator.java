package med.voll.api.service.validation.schedule;

import med.voll.api.domain.dtos.appointment.AppointmentScheduleData;
import med.voll.api.infra.exception.ValidException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ScheduleInAdvanceValidator implements AppointmentSchedulingValidator {

    public void validate(AppointmentScheduleData data) {
        var appointmentDate = data.date();
        var now = LocalDateTime.now();
        var differenceInMinutes = Duration.between(now, appointmentDate).toMinutes();
        if(differenceInMinutes < 30) throw new ValidException("Appointment must be scheduled at least 30 minutes in advance");
    }
}
