package med.voll.api.dtos.doctor;

import med.voll.api.doctor.Specialty;
import med.voll.api.models.Doctor;

public record DoctorListData(Long id, String name, String email, String crm, Specialty specialty) {
    public DoctorListData(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty());
    }
}
