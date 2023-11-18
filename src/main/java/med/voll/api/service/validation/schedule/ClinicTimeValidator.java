package med.voll.api.service.validation.schedule;

import med.voll.api.domain.dtos.appointment.AppointmentScheduleData;
import med.voll.api.infra.exception.ValidException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ClinicTimeValidator implements AppointmentSchedulingValidator{

    public void validate(AppointmentScheduleData data) {
        var appointmentDate = data.date();
        var sunday = appointmentDate.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var beforeClinicOpens = appointmentDate.getHour() < 7;
        var afterClinicCloses = appointmentDate.getHour() > 18;
        if(sunday || beforeClinicOpens || afterClinicCloses) throw new ValidException("Appointment outside clinic opening hours");
    }

}
