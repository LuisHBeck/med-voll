package med.voll.api.dtos.doctor;

import med.voll.api.doctor.Specialty;
import med.voll.api.jpas.Doctor;

public record DoctorListData(String name, String email, String crm, Specialty specialty) {
    public DoctorListData(Doctor doctor) {
        this(doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty());
    }
}
