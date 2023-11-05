package med.voll.api.dtos.doctor;

import med.voll.api.doctor.Specialty;
import med.voll.api.dtos.AddressData;
import med.voll.api.jpas.Address;
import med.voll.api.jpas.Doctor;

public record DoctorDetailingData(Long id, String name, String email, String crm, String phone, Specialty specialty, Address address) {
    public DoctorDetailingData(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getPhone(), doctor.getSpecialty(), doctor.getAddress());
    }
}
