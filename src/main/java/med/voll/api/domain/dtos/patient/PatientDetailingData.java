package med.voll.api.dtos.patient;

import med.voll.api.models.Address;
import med.voll.api.models.Patient;

public record PatientDetailingData(Long id, String name, String phone, String cpf, Address address) {
    public PatientDetailingData(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getPhone(), patient.getCpf(), patient.getAddress());
    }
}
