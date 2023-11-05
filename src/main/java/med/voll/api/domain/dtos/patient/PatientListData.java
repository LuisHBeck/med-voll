package med.voll.api.domain.dtos.patient;

import med.voll.api.domain.models.Patient;

public record PatientListData(Long id, String name, String email, String cpf) {
    public PatientListData (Patient patient){
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getCpf());
    }
}
