package med.voll.api.domain.dtos.patient;

import med.voll.api.domain.models.Address;
import med.voll.api.domain.models.Patient;

public record PatientDetailingData(Long id, String name, String phone, String cpf, Address address) {
    public PatientDetailingData(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getPhone(), patient.getCpf(), patient.getAddress());
    }
}
