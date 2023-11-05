package med.voll.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dtos.doctor.DoctorRegistrationData;
import med.voll.api.doctor.Specialty;
import med.voll.api.dtos.doctor.DoctorUpdateData;

@Table(name="Doctors")
@Entity(name="Doctor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phone;

    @Column(unique = true)
    private String crm;

    @Enumerated(EnumType.STRING)
    private Specialty specialty;

    @Embedded
    private Address address;

    private Boolean isActive;

    public Doctor(DoctorRegistrationData data) {
        this.name = data.name();
        this.email = data.email();
        this.phone = data.phone();
        this.crm = data.crm();
        this.specialty = data.specialty();
        this.address = new Address(data.address());
        this.isActive = true;
    }

    public void updateInformation(DoctorUpdateData data) {
        if (data.name() != null) {
            this.name = data.name();
        }
        if (data.phone() != null) {
            this.phone = data.phone();
        }
        if (data.address() != null) {
            this.address.updateInformation(data.address());
        }
    }

    public void logicalDeletion() {
        this.isActive = false;
    }
}
