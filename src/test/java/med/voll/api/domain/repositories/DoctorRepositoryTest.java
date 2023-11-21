package med.voll.api.domain.repositories;

import med.voll.api.domain.dtos.AddressData;
import med.voll.api.domain.dtos.doctor.DoctorRegistrationData;
import med.voll.api.domain.dtos.patient.PatientRegistrationData;
import med.voll.api.domain.models.Appointment;
import med.voll.api.domain.models.Doctor;
import med.voll.api.domain.models.Patient;
import med.voll.api.domain.models.Specialty;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class DoctorRepositoryTest {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private TestEntityManager em;


    @Test
    @DisplayName("Should return null when the only doctor isn't available on the date")
    public void chooseRandomDoctorAvailableScenario1() {
        var nextMondayAt10AM = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        var doctor = registerDoctor("Daniel Beck", "beck@vollmed.com", "19985145636", "123456", Specialty.CARDIOLOGY);
        var patient = registerPatient("Luís Beck", "beck@gmail.com", "19965421587", "48565812698");
        scheduleAppointment(doctor, patient, nextMondayAt10AM);

        var availableDoctor = doctorRepository.chooseRandomDoctorAvailable(Specialty.CARDIOLOGY, nextMondayAt10AM);
        assertThat(availableDoctor).isNull();
    }


    @Test
    @DisplayName("Should return doctor when the doctor is available on the date")
    public void chooseRandomDoctorAvailableScenario2() {
        var nextMondayAt10AM = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        var doctor = registerDoctor("Daniel Beck", "beck@vollmed.com", "19985145636", "123456", Specialty.CARDIOLOGY);

        var availableDoctor = doctorRepository.chooseRandomDoctorAvailable(Specialty.CARDIOLOGY, nextMondayAt10AM);
        assertThat(availableDoctor).isEqualTo(doctor);
    }

    private AddressData addressData() {
        return new AddressData("Test avenue",
                "Test neighborhood",
                "666999333",
                "São-Paulo",
                "SP",
                null,
                null);
    }

    private DoctorRegistrationData doctorData(String name, String email, String phone, String crm, Specialty specialty){
        return new DoctorRegistrationData(name,
                email,
                phone,
                crm,
                specialty,
                addressData());
    }

    private Doctor registerDoctor(String name, String email, String phone, String crm, Specialty specialty) {
        var doctor = new Doctor(doctorData(name,
                email,
                phone,
                crm,
                specialty));
        em.persist(doctor);
        return doctor;
    }

    private PatientRegistrationData patientData(String name, String email, String phone, String cpf) {
        return new PatientRegistrationData(name,
                email,
                phone,
                cpf,
                addressData());
    }

    private Patient registerPatient(String name, String email, String phone, String cpf) {
        var patient = new Patient(patientData(name,
                email,
                phone,
                cpf));
        em.persist(patient);
        return patient;
    }

    private void scheduleAppointment(Doctor doctor, Patient patient, LocalDateTime date) {
        em.persist(new Appointment(doctor, patient, date));
    }

}