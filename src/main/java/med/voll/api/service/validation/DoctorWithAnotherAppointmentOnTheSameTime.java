package med.voll.api.service.validation;

import med.voll.api.domain.dtos.appointment.AppointmentScheduleData;
import med.voll.api.domain.models.Appointment;
import med.voll.api.domain.repositories.AppointmentRepository;
import med.voll.api.domain.repositories.DoctorRepository;
import med.voll.api.infra.exception.ValidException;

public class DoctorWithAnotherAppointmentOnTheSameTime {

    private AppointmentRepository repository;

    public void valid(AppointmentScheduleData data) {
        var doctorHasAnotherAppointmentOnTheSameTime = repository.existsByDoctorIdAndDate(data.doctorId(), data.date());
        if(doctorHasAnotherAppointmentOnTheSameTime) throw new ValidException("Doctor isn't available at this time");
    }
}
