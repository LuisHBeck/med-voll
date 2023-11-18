package med.voll.api.service.validation.schedule;

import med.voll.api.domain.dtos.appointment.AppointmentScheduleData;

public interface AppointmentSchedulingValidator {
    void validate(AppointmentScheduleData data);
}
