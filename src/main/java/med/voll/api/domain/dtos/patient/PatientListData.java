package med.voll.api.dtos.patient;

import med.voll.api.models.Patient;

public record PatientListData(Long id, String name, String email, String cpf) {
    public PatientListData (Patient patient){
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getCpf());
    }
}
