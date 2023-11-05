package med.voll.api.dtos.doctor;

import med.voll.api.dtos.AddressData;

public record DoctorUpdateData(
        String name,
        String phone,
        AddressData address
) {
}
