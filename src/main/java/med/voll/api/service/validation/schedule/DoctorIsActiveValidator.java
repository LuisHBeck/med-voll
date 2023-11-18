package med.voll.api.service.validation.schedule;

import med.voll.api.domain.dtos.appointment.AppointmentScheduleData;
import med.voll.api.domain.repositories.DoctorRepository;
import med.voll.api.infra.exception.ValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoctorIsActiveValidator implements AppointmentSchedulingValidator {

    @Autowired
    private DoctorRepository repository;

    public void validate(AppointmentScheduleData data) {
        var doctorId = data.doctorId();
        if(doctorId == null) return;
        var isDoctorActive = repository.findIsActiveById(doctorId);
        if(!isDoctorActive) throw new ValidException("To make an appointment the doctor must be active");
    }
}
