package med.voll.api.dtos.patient;

import med.voll.api.jpas.Patient;

public record PatientListData(String name, String email, String cpf) {
    public PatientListData (Patient patient){
        this(patient.getName(), patient.getEmail(), patient.getCpf());
    }
}
