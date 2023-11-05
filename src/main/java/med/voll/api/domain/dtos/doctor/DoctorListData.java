package med.voll.api.domain.dtos.doctor;

import med.voll.api.domain.models.Doctor;
import med.voll.api.domain.models.Specialty;

public record DoctorListData(Long id, String name, String email, String crm, Specialty specialty) {
    public DoctorListData(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty());
    }
}
