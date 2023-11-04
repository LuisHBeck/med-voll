package med.voll.api.dtos.patient;

import med.voll.api.dtos.AddressData;
import med.voll.api.jpas.Address;

public record PatientUpdateData(
        Long id,
        String name,
        String phone,
        AddressData address
) {
}