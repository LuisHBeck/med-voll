package med.voll.api.service.validation.schedule;

import med.voll.api.domain.dtos.appointment.AppointmentScheduleData;
import med.voll.api.domain.repositories.AppointmentRepository;
import med.voll.api.infra.exception.ValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientWithoutAnotherAppointmentOnTheDay implements AppointmentSchedulingValidator {

    @Autowired
    private AppointmentRepository repository;

    public void validate(AppointmentScheduleData data) {
        var appointmentData = data.date();
        var firstTime = appointmentData.withHour(7);
        var lastTime = appointmentData.withHour(18);
        var patientHasAnotherAppointmentOnTheDay = repository.
                existsByPatientIdAndDateBetween(data.patientId(), firstTime, lastTime);
        if(patientHasAnotherAppointmentOnTheDay) throw new ValidException("Patient can't schedule two appointments on the same day");
    }
}
