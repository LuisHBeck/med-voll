package med.voll.api.service.validation.schedule;

import med.voll.api.domain.dtos.appointment.AppointmentScheduleData;
import med.voll.api.domain.repositories.PatientRepository;
import med.voll.api.infra.exception.ValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientIsActiveValidator implements AppointmentSchedulingValidator {

    @Autowired
    private PatientRepository repository;

    public void validate(AppointmentScheduleData data) {
        var isPatientActive = repository.findIsActiveById(data.patientId());
        if(!isPatientActive) throw new ValidException("To make an appointment the patient must be active");
    }
}
