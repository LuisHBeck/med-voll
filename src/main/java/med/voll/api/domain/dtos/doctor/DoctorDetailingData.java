package med.voll.api.domain.dtos.doctor;

import med.voll.api.domain.models.Address;
import med.voll.api.domain.models.Doctor;
import med.voll.api.domain.models.Specialty;

public record DoctorDetailingData(Long id, String name, String email, String crm, String phone, Specialty specialty, Address address) {
    public DoctorDetailingData(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getPhone(), doctor.getSpecialty(), doctor.getAddress());
    }
}
