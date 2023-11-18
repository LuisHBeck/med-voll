package med.voll.api.service.validation.cancel;

import med.voll.api.domain.dtos.appointment.AppointmentCancelData;
import med.voll.api.domain.repositories.AppointmentRepository;
import med.voll.api.infra.exception.ValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class CancelInAdvanceValidator implements AppointmentCancelingValidator{

    @Autowired
    private AppointmentRepository repository;

    @Override
    public void validate(AppointmentCancelData data) {
        var appointment = repository.getReferenceById(data.id());
        var now = LocalDateTime.now();
        var differenceInHours = Duration.between(now, appointment.getDate()).toHours();
        if(differenceInHours < 24) throw new ValidException("It's only possible to cancel an appointment 24 hours in advance");
    }
}
