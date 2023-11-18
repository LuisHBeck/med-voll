package med.voll.api.service.validation.schedule;

import med.voll.api.domain.dtos.appointment.AppointmentScheduleData;
import med.voll.api.domain.repositories.AppointmentRepository;
import med.voll.api.infra.exception.ValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoctorWithAnotherAppointmentOnTheSameTime implements AppointmentSchedulingValidator {

    @Autowired
    private AppointmentRepository repository;

    public void validate(AppointmentScheduleData data) {
        var doctorHasAnotherAppointmentOnTheSameTime = repository.existsByDoctorIdAndDate(data.doctorId(), data.date());
        if(doctorHasAnotherAppointmentOnTheSameTime) throw new ValidException("Doctor isn't available at this time");
    }
}
