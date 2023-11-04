package med.voll.api.controllers;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dtos.patient.PatientListData;
import med.voll.api.dtos.patient.PatientRegistrationData;
import med.voll.api.jpas.Patient;
import med.voll.api.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientRepository repository;

    @PostMapping
    @Transactional
    public void register(@RequestBody @Valid PatientRegistrationData data) {
        repository.save(new Patient(data));
    }

    @GetMapping
    public Page<PatientListData> list(@PageableDefault(size = 10) Pageable pagination) {
        return repository.findAll(pagination).map(PatientListData::new);
    }
}
