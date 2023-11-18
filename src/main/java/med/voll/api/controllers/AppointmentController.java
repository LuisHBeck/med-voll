package med.voll.api.controllers;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.dtos.appointment.AppointmentCancelData;
import med.voll.api.domain.dtos.appointment.AppointmentDetailingData;
import med.voll.api.domain.dtos.appointment.AppointmentScheduleData;
import med.voll.api.service.AppointmentCancelService;
import med.voll.api.service.AppointmentScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentScheduleService scheduleService;

    @Autowired
    AppointmentCancelService cancelService;

    @PostMapping
    @Transactional
    public ResponseEntity schedule(@RequestBody @Valid AppointmentScheduleData data) {
        System.out.println(data);
        var appointment = scheduleService.schedule(data);
        return ResponseEntity.ok(appointment);
    }

    @PostMapping("/cancel")
    @Transactional
    public ResponseEntity cancel(@RequestBody @Valid AppointmentCancelData data) {
        cancelService.cancel(data);
        return ResponseEntity.noContent().build();
    }
}
