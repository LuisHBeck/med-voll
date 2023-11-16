package med.voll.api.domain.repositories;

import med.voll.api.domain.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Boolean existsByPatientIdAndDateBetween(Long id, LocalDateTime firstTime, LocalDateTime lastTime);

    Boolean existsByDoctorIdAndDate(Long id, LocalDateTime date);
}
