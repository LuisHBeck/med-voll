package med.voll.api.dtos.patient;

import med.voll.api.jpas.Address;
import med.voll.api.jpas.Patient;

public record PatientDetailingData(Long id, String name, String phone, String cpf, Address address) {
    public PatientDetailingData(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getPhone(), patient.getCpf(), patient.getAddress());
    }
}
