package med.voll.api.controller;

import med.voll.api.doctor.DoctorRegistrationData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    @PostMapping
    public void register(@RequestBody DoctorRegistrationData data) {
        System.out.println(data);
    }
}
