package med.voll.api.dtos.doctor;

import jakarta.validation.constraints.NotNull;
import med.voll.api.dtos.AddressData;
import med.voll.api.jpas.Address;

public record DoctorUpdateData(
        String name,
        String phone,
        AddressData address
) {
}
