package med.voll.api.service;

import med.voll.api.domain.dtos.appointment.AppointmentCancelData;
import med.voll.api.domain.repositories.AppointmentRepository;
import med.voll.api.service.validation.cancel.AppointmentCancelingValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentCancelService {

    @Autowired
    private AppointmentRepository repository;

    @Autowired
    private List<AppointmentCancelingValidator> validators;

    public void cancel(AppointmentCancelData data) {
        validators.forEach(v -> v.validate(data));

        var appointment = repository.getReferenceById(data.id());
        repository.delete(appointment);
    }
}
