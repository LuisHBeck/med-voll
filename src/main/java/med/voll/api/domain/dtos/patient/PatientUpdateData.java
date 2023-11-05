package med.voll.api.domain.dtos.patient;

import med.voll.api.domain.dtos.AddressData;

public record PatientUpdateData(
        String name,
        String phone,
        AddressData address
) {
}
