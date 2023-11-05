package med.voll.api.dtos.patient;

import med.voll.api.dtos.AddressData;

public record PatientUpdateData(
        String name,
        String phone,
        AddressData address
) {
}
