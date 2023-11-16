package med.voll.api.service.validation;

import med.voll.api.domain.dtos.appointment.AppointmentScheduleData;
import med.voll.api.domain.repositories.AppointmentRepository;
import med.voll.api.infra.exception.ValidException;

import java.time.LocalDateTime;

public class PatientWithoutAnotherAppointmentOnTheDay {

    private AppointmentRepository repository;

    public void valid(AppointmentScheduleData data) {
        var appointmentData = data.date();
        var firstTime = appointmentData.withHour(7);
        var lastTime = appointmentData.withHour(18);
        var patientHasAnotherAppointmentOnTheDay = repository.
                existsByPatientIdAndDateBetween(data.patientId(), firstTime, lastTime);
        if(patientHasAnotherAppointmentOnTheDay) throw new ValidException("Patient can't schedule two appointments on the same day");
    }
}
