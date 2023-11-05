package med.voll.api.controllers;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.dtos.patient.PatientDetailingData;
import med.voll.api.domain.dtos.patient.PatientListData;
import med.voll.api.domain.dtos.patient.PatientRegistrationData;
import med.voll.api.domain.dtos.patient.PatientUpdateData;
import med.voll.api.domain.models.Patient;
import med.voll.api.domain.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid PatientRegistrationData data, UriComponentsBuilder uriBuilder) {
        var patient = new Patient(data);
        repository.save(patient);
        var uri = uriBuilder.path("/patients/{id}").buildAndExpand(patient.getId()).toUri();
        return ResponseEntity.created(uri).body(new PatientDetailingData(patient));
    }

    @GetMapping
    public ResponseEntity<Page<PatientListData>> list(@PageableDefault(size = 10) Pageable pagination) {
        var page = repository.findAllByIsActiveTrue(pagination).map(PatientListData::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity listById(@PathVariable Long id) {
        var patient = repository.getReferenceById(id);
        return ResponseEntity.ok(new PatientDetailingData(patient));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity update(@PathVariable Long id, @RequestBody @Valid PatientUpdateData data) {
        var patient = repository.getReferenceById(id);
        patient.updateInformation(data);
        return ResponseEntity.ok(new PatientDetailingData(patient));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        var patient = repository.getReferenceById(id);
        patient.logicalDeletion();
        return ResponseEntity.noContent().build();
    }
}
