package med.voll.api.domain.dtos.doctor;

import med.voll.api.domain.dtos.AddressData;

public record DoctorUpdateData(
        String name,
        String phone,
        AddressData address
) {
}
