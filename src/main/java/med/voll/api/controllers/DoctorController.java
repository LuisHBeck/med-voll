package med.voll.api.controllers;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dtos.doctor.DoctorListData;
import med.voll.api.dtos.doctor.DoctorUpdateData;
import med.voll.api.jpas.Doctor;
import med.voll.api.dtos.doctor.DoctorRegistrationData;
import med.voll.api.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;


@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository repository;

    @PostMapping
    @Transactional
    public void register(@RequestBody @Valid DoctorRegistrationData data) {
        repository.save(new Doctor(data));
    }

    @GetMapping
    public Page<DoctorListData> list(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination) {
        return repository.findAllByIsActiveTrue(pagination).map(DoctorListData::new);
    }

    @GetMapping("/{id}")
    public Stream<DoctorListData> listById(@PathVariable Long id) {
        return repository.findById(id).stream().map(DoctorListData::new);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid DoctorUpdateData data) {
        var doctor = repository.getReferenceById(data.id());
        doctor.updateInformation(data);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id) {
        var doctor = repository.getReferenceById(id);
        doctor.logicalDeletion();
    }
}