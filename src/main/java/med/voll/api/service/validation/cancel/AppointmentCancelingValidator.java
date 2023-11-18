package med.voll.api.service.validation.cancel;

import med.voll.api.domain.dtos.appointment.AppointmentCancelData;

public interface AppointmentCancelingValidator {
    void validate(AppointmentCancelData data);
}
