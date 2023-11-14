package med.voll.api.controllers;

import jakarta.validation.Valid;
import med.voll.api.domain.dtos.appointment.AppointmentDetailingData;
import med.voll.api.domain.dtos.appointment.AppointmentScheduleData;
import med.voll.api.service.AppointmentScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentScheduleService service;

    @PostMapping
    public ResponseEntity schedule(@RequestBody @Valid AppointmentScheduleData data) {
        System.out.println(data);
        service.schedule(data);
        return ResponseEntity.ok(new AppointmentDetailingData(null, null, null, null));
    }
}
